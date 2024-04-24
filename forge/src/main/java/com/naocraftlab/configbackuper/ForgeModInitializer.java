package com.naocraftlab.configbackuper;

import com.naocraftlab.configbackuper.core.BackupLimiter;
import com.naocraftlab.configbackuper.core.ConfigBackuper;
import com.naocraftlab.configbackuper.core.ModConfig;
import com.naocraftlab.configbackuper.core.ModConfigurationManager;
import com.naocraftlab.configbackuper.util.LoggerWrapper;
import com.naocraftlab.configbackuper.util.LoggerWrapperLog4j;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.logging.log4j.LogManager;

import java.nio.file.Path;

@Mod("configbackuper")
public class ForgeModInitializer
{
    private static final LoggerWrapper LOGGER = new LoggerWrapperLog4j(LogManager.getLogger());

    private ModConfigurationManager modConfigurationManager;
    private ConfigBackuper configBackuper;
    private BackupLimiter backupLimiter;

    public ForgeModInitializer() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        final Path configFile = FMLPaths.CONFIGDIR.get().resolve("config-backuper.json");
        modConfigurationManager = new ModConfigurationManager(LOGGER, configFile);

        final ModConfig modConfig = modConfigurationManager.read();
        configBackuper = new ConfigBackuper(LOGGER, modConfig);
        backupLimiter = new BackupLimiter(LOGGER, modConfig);

        configBackuper.performBackup();
        backupLimiter.removeOldBackups();
    }
}

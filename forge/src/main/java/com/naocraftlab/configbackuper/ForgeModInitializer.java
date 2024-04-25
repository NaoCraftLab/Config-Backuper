package com.naocraftlab.configbackuper;

import com.naocraftlab.configbackuper.core.BackupLimiter;
import com.naocraftlab.configbackuper.core.ConfigBackuper;
import com.naocraftlab.configbackuper.core.ModConfig;
import com.naocraftlab.configbackuper.core.ModConfigurationManager;
import com.naocraftlab.configbackuper.util.LoggerWrapper;
import com.naocraftlab.configbackuper.util.LoggerWrapperLog4j;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import org.apache.logging.log4j.LogManager;

import java.nio.file.Path;
import java.nio.file.Paths;

@Mod(modid = "configbackuper", version = "1.0.1")
public class ForgeModInitializer {

    private static final LoggerWrapper LOGGER = new LoggerWrapperLog4j(LogManager.getLogger("Config Backuper"));

    @EventHandler
    public void init(FMLInitializationEvent event) {
        final Path configFile = Paths.get("./config").resolve("config-backuper.json");
        final ModConfigurationManager modConfigurationManager = new ModConfigurationManager(LOGGER, configFile);

        final ModConfig modConfig = modConfigurationManager.read();
        final ConfigBackuper configBackuper = new ConfigBackuper(LOGGER, modConfig);
        final BackupLimiter backupLimiter = new BackupLimiter(LOGGER, modConfig);

        configBackuper.performBackup();
        backupLimiter.removeOldBackups();
    }
}

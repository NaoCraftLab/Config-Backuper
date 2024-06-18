package com.naocraftlab.configbackuper;

import com.mojang.logging.LogUtils;
import com.naocraftlab.configbackuper.core.BackupLimiter;
import com.naocraftlab.configbackuper.core.ConfigBackuper;
import com.naocraftlab.configbackuper.core.ModConfig;
import com.naocraftlab.configbackuper.core.ModConfigurationManager;
import com.naocraftlab.configbackuper.util.LoggerWrapper;
import com.naocraftlab.configbackuper.util.LoggerWrapperSlf4j;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.nio.file.Path;

import static net.minecraftforge.api.distmarker.Dist.CLIENT;
import static net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus.MOD;
import static net.minecraftforge.fml.loading.FMLPaths.CONFIGDIR;

@Mod("configbackuper")
public class ForgeModInitializer {

    private static final LoggerWrapper LOGGER = new LoggerWrapperSlf4j(LogUtils.getLogger());

    @Mod.EventBusSubscriber(modid = "configbackuper", bus = MOD, value = CLIENT)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            final Path configFile = CONFIGDIR.get().resolve("config-backuper.json");
            final ModConfigurationManager modConfigurationManager = new ModConfigurationManager(LOGGER, configFile);

            final ModConfig modConfig = modConfigurationManager.read();
            final ConfigBackuper configBackuper = new ConfigBackuper(LOGGER, modConfig);
            final BackupLimiter backupLimiter = new BackupLimiter(LOGGER, modConfig);

            configBackuper.performBackup();
            backupLimiter.removeOldBackups();
        }
    }
}

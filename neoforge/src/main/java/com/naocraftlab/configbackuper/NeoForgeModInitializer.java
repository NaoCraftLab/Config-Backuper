package com.naocraftlab.configbackuper;

import com.mojang.logging.LogUtils;
import com.naocraftlab.configbackuper.core.BackupLimiter;
import com.naocraftlab.configbackuper.core.ConfigBackuper;
import com.naocraftlab.configbackuper.core.ModConfig;
import com.naocraftlab.configbackuper.core.ModConfigurationManager;
import com.naocraftlab.configbackuper.util.LoggerWrapper;
import com.naocraftlab.configbackuper.util.LoggerWrapperSlf4j;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

import java.nio.file.Path;

import static net.neoforged.api.distmarker.Dist.CLIENT;
import static net.neoforged.fml.common.EventBusSubscriber.Bus.MOD;
import static net.neoforged.fml.loading.FMLPaths.CONFIGDIR;

@Mod(NeoForgeModInitializer.MOD_ID)
public class NeoForgeModInitializer {

    public static final String MOD_ID = "configbackuper";
    private static final LoggerWrapper LOGGER = new LoggerWrapperSlf4j(LogUtils.getLogger());

    public NeoForgeModInitializer(IEventBus modEventBus, ModContainer modContainer) {}

    @EventBusSubscriber(modid = MOD_ID, bus = MOD, value = CLIENT)
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

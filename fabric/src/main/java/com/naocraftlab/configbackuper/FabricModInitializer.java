package com.naocraftlab.configbackuper;

import com.naocraftlab.configbackuper.common.BackupLimiter;
import com.naocraftlab.configbackuper.common.ConfigBackuper;
import com.naocraftlab.configbackuper.common.CriticalConfigBackuperException;
import com.naocraftlab.configbackuper.common.ModConfig;
import com.naocraftlab.configbackuper.common.ModConfigurationManager;
import com.naocraftlab.configbackuper.util.LoggerWrapper;
import com.naocraftlab.configbackuper.util.LoggerWrapperLog4j;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.fabricmc.loader.api.metadata.ModMetadata;
import org.apache.logging.log4j.LogManager;

import java.nio.file.Path;

public class FabricModInitializer implements ModInitializer {

    private static final ModMetadata MOD_METADATA = FabricLoader.getInstance()
            .getModContainer("config-backuper")
            .map(ModContainer::getMetadata)
            .orElseThrow(() -> new CriticalConfigBackuperException("Failed to get mod metadata"));
    private static final LoggerWrapper LOGGER = new LoggerWrapperLog4j(LogManager.getLogger(MOD_METADATA.getName()));

    private ModConfigurationManager modConfigurationManager;
    private ConfigBackuper configBackuper;
    private BackupLimiter backupLimiter;

    @Override
    public void onInitialize() {
        try {
            initScript();
        } catch (Exception e) {
            throw new CriticalConfigBackuperException(MOD_METADATA.getName() + " error", e);
        }
    }

    private void initScript() {
        final Path configFile = FabricLoader.getInstance().getConfigDir().resolve(MOD_METADATA.getId() + ".json");
        modConfigurationManager = new ModConfigurationManager(LOGGER, configFile);

        final ModConfig modConfig = modConfigurationManager.read();
        configBackuper = new ConfigBackuper(LOGGER, modConfig);
        backupLimiter = new BackupLimiter(LOGGER, modConfig);

        configBackuper.performBackup();
        backupLimiter.removeOldBackups();
    }
}

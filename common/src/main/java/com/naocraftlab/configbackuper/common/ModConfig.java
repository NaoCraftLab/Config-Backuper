package com.naocraftlab.configbackuper.common;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ModConfig {

    private final boolean includeGameConfigs;
    private final boolean includeModConfigs;
    private final boolean includeShaderPackConfigs;
    private final int maxBackups;
    private final boolean compressionEnabled;
    private final String backupFolder;
    private final String backupFilePrefix;
    private final String backupFileSuffix;

    public ModConfig() {
        this(true, true, true, 10, false, "./", "config_backup__", "");
    }

    public ModConfig(
            boolean includeGameConfigs,
            boolean includeModConfigs,
            boolean includeShaderPackConfigs,
            int maxBackups,
            boolean compressionEnabled,
            String backupFolder,
            String backupFilePrefix,
            String backupFileSuffix
    ) {
        this.includeGameConfigs = includeGameConfigs;
        this.includeModConfigs = includeModConfigs;
        this.includeShaderPackConfigs = includeShaderPackConfigs;
        this.maxBackups = maxBackups;
        this.compressionEnabled = compressionEnabled;
        this.backupFolder = backupFolder;
        this.backupFilePrefix = backupFilePrefix;
        this.backupFileSuffix = backupFileSuffix;
    }

    public boolean isIncludeGameConfigs() {
        return includeGameConfigs;
    }

    public boolean isIncludeModConfigs() {
        return includeModConfigs;
    }

    public boolean isIncludeShaderPackConfigs() {
        return includeShaderPackConfigs;
    }

    public int getMaxBackups() {
        return maxBackups;
    }

    public boolean isCompressionEnabled() {
        return compressionEnabled;
    }

    public Path getBackupFolder() {
        return Paths.get(backupFolder);
    }

    public String getBackupFilePrefix() {
        return backupFilePrefix;
    }

    public String getBackupFileSuffix() {
        return backupFileSuffix;
    }
}

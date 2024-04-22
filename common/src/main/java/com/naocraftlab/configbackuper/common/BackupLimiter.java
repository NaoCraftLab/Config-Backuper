package com.naocraftlab.configbackuper.common;

import com.naocraftlab.configbackuper.util.LoggerWrapper;

import java.io.File;

import static com.naocraftlab.configbackuper.common.ConfigBackuper.BACKUP_EXTENSION;
import static java.util.Arrays.sort;
import static java.util.Comparator.comparingLong;

public class BackupLimiter {

    private final LoggerWrapper log;
    private final ModConfig modConfig;

    public BackupLimiter(LoggerWrapper log, ModConfig modConfig) {
        this.log = log;
        this.modConfig = modConfig;
    }

    public void removeOldBackups() {
        if (modConfig.getMaxBackups() < 0) {
            log.info("Automatic deletion of old backups is disabled");
            return;
        }

        final File[] backups = modConfig.getBackupFolder().toFile().listFiles((dir, name) -> isBackupFile(name));
        if (backups != null && backups.length > modConfig.getMaxBackups()) {
            sort(backups, comparingLong(File::lastModified).reversed());
            for (int i = modConfig.getMaxBackups(); i < backups.length; i++) {
                final File backup = backups[i];
                if (backup.delete()) {
                    log.info("Deleted old backup file: {}", backup.getName());
                } else {
                    log.error("Failed to delete old backup file: {}", backup.getName());
                }
            }
        }
    }

    private boolean isBackupFile(String fileName) {
        return fileName.startsWith(modConfig.getBackupFilePrefix())
                && fileName.endsWith(modConfig.getBackupFileSuffix() + BACKUP_EXTENSION);
    }
}

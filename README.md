# <img src="src/main/resources/assets/config-backuper/icon.png" width="28"> Config Backuper

A simple mod for backing up all configurations at the start of Minecraft.

## Features

- Backs up configurations at the start of Minecraft
- Backs up game configurations
- Backs up mod configurations
- Backs up shader configurations
- Compression of backups
- Removes old backups

## Configuration

The configuration file is located at `config/config-backuper.json`.

- `includeGameConfigs` - add game configurations to the backup
- `includeModConfigs` - add mod configurations to the backup
- `includeShaderPackConfigs` - add shader configurations to the backup
- `maxBackups` - Maximum number of stored backups (`-1` disables deletion of old backups, default `10`)
- `compressionEnabled` - use compression for backups (enabling compression reduces the size of the backup, but slows down its creation a bit)
- `backupFolder` - directory for backups (`./` - current game directory)
- `backupFilePrefix` - prefix for backup file names
- `backupFileSuffix` - suffix for backup file names

## Modpacks

You can use this mod in modpacks without requesting permission.

## Development

### Prerequisites

- JDK 17

### Building

```shell
./gradlew build
```

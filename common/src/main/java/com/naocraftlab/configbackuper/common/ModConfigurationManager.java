package com.naocraftlab.configbackuper.common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.naocraftlab.configbackuper.util.LoggerWrapper;

import java.io.IOException;
import java.nio.file.Path;

import static com.naocraftlab.configbackuper.util.Files.readString;
import static com.naocraftlab.configbackuper.util.Files.writeString;
import static java.nio.file.Files.notExists;

public class ModConfigurationManager {

    private final LoggerWrapper log;
    private final Path configPath;

    private final Gson gson;

    public ModConfigurationManager(LoggerWrapper log, Path configPath) {
        this.log = log;
        this.configPath = configPath;

        gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
    }

    public ModConfig read() {
        log.info("Reading mod configuration");
        if (notExists(configPath)) {
            log.warn("Mod configuration not found");
            return writeDefault();
        }
        final String json;
        try {
            json = readString(configPath);
        } catch (IOException e) {
            throw new NestedConfigBackuperException(e);
        }
        final ModConfig modConfig = gson.fromJson(json, ModConfig.class);
        return write(modConfig);
    }

    public ModConfig write(ModConfig modConfig) {
        log.info("Writing mod configuration");
        final String json = gson.toJson(modConfig);
        try {
            writeString(configPath, json);
        } catch (IOException e) {
            throw new NestedConfigBackuperException(e);
        }
        return modConfig;
    }

    public ModConfig writeDefault() {
        return write(new ModConfig());
    }
}

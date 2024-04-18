package com.naocraftlab.configbackuper.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;

import java.io.IOException;
import java.nio.file.Path;

import static java.nio.file.Files.notExists;
import static java.nio.file.Files.readString;
import static java.nio.file.Files.writeString;

public class ModConfigurationManager {

    private final Logger log;
    private final Path configPath;

    private final Gson gson;

    public ModConfigurationManager(Logger log, Path configPath) {
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

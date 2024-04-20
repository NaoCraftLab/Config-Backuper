package com.naocraftlab.configbackuper.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Path;

import static java.lang.System.lineSeparator;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.Files.newBufferedReader;
import static java.nio.file.Files.newBufferedWriter;

public final class Files {

    private Files() {
        throw new UnsupportedOperationException();
    }

    public static String readString(Path path) throws IOException {
        final StringBuilder stringBuilder = new StringBuilder();
        try (final BufferedReader reader = newBufferedReader(path, UTF_8)) {
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append(lineSeparator());
            }
        }
        return stringBuilder.toString();
    }

    public static Path writeString(Path path, CharSequence charSequence) throws IOException {
        try (final BufferedWriter writer = newBufferedWriter(path, UTF_8)) {
            writer.append(charSequence);
        }
        return path;
    }
}

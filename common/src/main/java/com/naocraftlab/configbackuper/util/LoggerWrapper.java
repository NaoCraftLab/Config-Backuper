package com.naocraftlab.configbackuper.util;

public interface LoggerWrapper {

    void error(String message, Object... args);

    void warn(String message, Object... args);

    void info(String message, Object... args);

    void debug(String message, Object... args);

    void trace(String message, Object... args);
}

package com.naocraftlab.configbackuper.util;

import org.slf4j.Logger;

public class LoggerWrapperSlf4j implements LoggerWrapper {

    private final Logger logger;

    public LoggerWrapperSlf4j(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void error(String message, Object... args) {
        logger.error(message, args);
    }

    @Override
    public void warn(String message, Object... args) {
        logger.warn(message, args);
    }

    @Override
    public void info(String message, Object... args) {
        logger.info(message, args);
    }

    @Override
    public void debug(String message, Object... args) {
        logger.debug(message, args);
    }

    @Override
    public void trace(String message, Object... args) {
        logger.trace(message, args);
    }
}

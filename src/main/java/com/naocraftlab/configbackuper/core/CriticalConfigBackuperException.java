package com.naocraftlab.configbackuper.core;

public class CriticalConfigBackuperException extends RuntimeException {

    public CriticalConfigBackuperException(String message) {
        super(message);
    }

    public CriticalConfigBackuperException(String message, Throwable cause) {
        super(message, cause);
    }
}

package com.naocraftlab.configbackuper.common;

public class CriticalConfigBackuperException extends RuntimeException {

    public CriticalConfigBackuperException(String message) {
        super(message);
    }

    public CriticalConfigBackuperException(String message, Throwable cause) {
        super(message, cause);
    }
}

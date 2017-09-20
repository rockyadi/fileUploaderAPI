package com.exception;

public class FileArchiveException extends RuntimeException {

    public FileArchiveException(String message) {
        super(message);
    }

    public FileArchiveException(String message, Throwable cause) {
        super(message, cause);
    }
}


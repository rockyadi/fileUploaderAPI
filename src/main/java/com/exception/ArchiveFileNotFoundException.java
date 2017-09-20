package com.exception;

public class ArchiveFileNotFoundException extends FileArchiveException {

    public ArchiveFileNotFoundException(String message) {
        super(message);
    }

    public ArchiveFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
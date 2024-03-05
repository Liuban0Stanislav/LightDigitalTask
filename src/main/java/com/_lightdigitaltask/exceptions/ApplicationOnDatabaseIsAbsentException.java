package com._lightdigitaltask.exceptions;

public class ApplicationOnDatabaseIsAbsentException extends RuntimeException{
    public ApplicationOnDatabaseIsAbsentException() {
    }

    public ApplicationOnDatabaseIsAbsentException(String message) {
        super(message);
    }

    public ApplicationOnDatabaseIsAbsentException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApplicationOnDatabaseIsAbsentException(Throwable cause) {
        super(cause);
    }

    public ApplicationOnDatabaseIsAbsentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

package com._lightdigitaltask.exceptions;

/**
 * Класс исключения, которое говорит о том, что искоомый пользователь на нейден в базе данных.
 * @Версия: 1.0
 * @Дата: 05.03.2024
 * @Автор: Станислав Любань
 */
public class UserOnDatabaseIsAbsentException extends RuntimeException{
    public UserOnDatabaseIsAbsentException() {
    }

    public UserOnDatabaseIsAbsentException(String message) {
        super(message);
    }

    public UserOnDatabaseIsAbsentException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserOnDatabaseIsAbsentException(Throwable cause) {
        super(cause);
    }

    public UserOnDatabaseIsAbsentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

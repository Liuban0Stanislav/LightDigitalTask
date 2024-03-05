package com._lightdigitaltask.exceptions;

/**
 * Класс исключения, появление которго, говорит о том, что искомый аватар отсутствует в БД.
 * @Версия: 1.0
 * @Дата: 04.03.2024
 * @Автор: Станислав Любань
 */
public class PhotoOnDatabaseIsAbsentException extends RuntimeException{
    public PhotoOnDatabaseIsAbsentException() {
        System.out.println("Ошибка: фото отсутствует в базе данных");
    }

    public PhotoOnDatabaseIsAbsentException(String message) {
        super(message);
    }

    public PhotoOnDatabaseIsAbsentException(String message, Throwable cause) {
        super(message, cause);
    }

    public PhotoOnDatabaseIsAbsentException(Throwable cause) {
        super(cause);
    }

    public PhotoOnDatabaseIsAbsentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

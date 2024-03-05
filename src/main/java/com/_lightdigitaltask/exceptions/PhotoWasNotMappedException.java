package com._lightdigitaltask.exceptions;

/**
 * Класс исключения, которое говорит о том, что не был выполнен маппинг из объекта типа
 * {@link org.springframework.web.multipart.MultipartFile} в объект типа {@link com._lightdigitaltask.models.Photo}
 * @Версия: 1.0
 * @Дата: 04.03.2024
 * @Автор: Станислав Любань
 */
public class PhotoWasNotMappedException extends RuntimeException{
    public PhotoWasNotMappedException() {
    }

    public PhotoWasNotMappedException(String message) {
        super(message);
    }

    public PhotoWasNotMappedException(String message, Throwable cause) {
        super(message, cause);
    }

    public PhotoWasNotMappedException(Throwable cause) {
        super(cause);
    }

    public PhotoWasNotMappedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

package com._lightdigitaltask.exceptions;

public class MultipartFileToPhotoException extends RuntimeException{
    public MultipartFileToPhotoException() {
        System.out.println("Ошибка конвертации MultipartFile в Photo");
    }

    public MultipartFileToPhotoException(String message) {
        super(message);
    }

    public MultipartFileToPhotoException(String message, Throwable cause) {
        super(message, cause);
    }

    public MultipartFileToPhotoException(Throwable cause) {
        super(cause);
    }

    public MultipartFileToPhotoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

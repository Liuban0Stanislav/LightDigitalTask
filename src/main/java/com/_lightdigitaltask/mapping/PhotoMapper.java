package com._lightdigitaltask.mapping;


import com._lightdigitaltask.exceptions.MultipartFileToPhotoException;
import com._lightdigitaltask.models.Photo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static com._lightdigitaltask.servlet.MethodInspector.getCurrentMethodName;

/**
 * Класс-маппер, нужен для конвертации объектов {@link org.springframework.web.multipart.MultipartFile} в
 * {@link Photo}.
 * @Версия: 1.0
 * @Дата: 05.03.2024
 * @Автор: Станислав Любань
 */
@Slf4j
@Component
public class PhotoMapper {
    /**
     * {@link MultipartFile} -> {@link Photo}
     * @param image {@link MultipartFile}
     * @return {@link Photo}
     */
    public Photo mapMuptipartFileToPhoto(MultipartFile image) {
        log.info("Запущен метод сервиса {}", getCurrentMethodName());
        Photo photo = new Photo();
        try {
            photo.setFileSize(image.getSize());
            photo.setMediaType(image.getContentType());
            photo.setData(image.getBytes());
        } catch (IOException e) {
            throw new MultipartFileToPhotoException(" Место ошибки - userMapper.mapMultiPartFileToPhoto()");
        }
        return photo;
    }
}

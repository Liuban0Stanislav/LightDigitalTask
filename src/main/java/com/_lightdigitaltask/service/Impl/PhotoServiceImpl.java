package com._lightdigitaltask.service.Impl;

import com._lightdigitaltask.exceptions.PhotoOnDatabaseIsAbsentException;
import com._lightdigitaltask.exceptions.PhotoWasNotMappedException;
import com._lightdigitaltask.mapping.PhotoMapper;
import com._lightdigitaltask.mapping.UserMapper;
import com._lightdigitaltask.models.Photo;
import com._lightdigitaltask.models.User;
import com._lightdigitaltask.repository.PhotoRepository;
import com._lightdigitaltask.repository.UserRepository;
import com._lightdigitaltask.service.PhotoService;
import com._lightdigitaltask.servlet.MethodInspector;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import static java.nio.file.StandardOpenOption.CREATE_NEW;

/**
 * Класс сервиса отвечающий за работу с аватаром пользователя.
 *
 * @Версия: 1.0
 * @Дата: 04.03.2024
 * @Автор: Станислав Любань
 */
@Service
@Slf4j
public class PhotoServiceImpl implements PhotoService {
    private final PhotoRepository photoRepository;
    private final UserRepository userRepository;
    private final PhotoMapper photoMapper;

    public PhotoServiceImpl(PhotoRepository photoRepository, UserRepository userRepository, PhotoMapper photoMapper) {
        this.photoRepository = photoRepository;
        this.userRepository = userRepository;
        this.photoMapper = photoMapper;
    }

    /**
     * Метод возвращает фото с ПК, а если его там нет по каким-то причинам,
     * то перенаправляет запрос на получение фото в базу данных.
     *
     * @param photoId
     * @return byte[] массив байт
     * @throws IOException
     */
    public byte[] getPhoto(Integer photoId) throws NoSuchFieldException {
        log.info("Запущен метод сервиса {}", MethodInspector.getCurrentMethodName());
        log.info("photoId: {}", photoId);

        //получаем фото из БД
        Photo photo;
        if (photoRepository.findById(photoId).isPresent()) {
            photo = photoRepository.findById(photoId).get();
            return photo.getData();
        } else {
            throw new PhotoOnDatabaseIsAbsentException("Фото отсутствует в базе данных.");
        }
    }

    /**
     * Метод добавляет аватар пользователя или изменяет его, если аватар существует.
     * @param image - файл аватара
     * @param userId - id пользователя, которому добавляется аватар
     * @return - {@link Photo}
     */
    @Override
    public Photo updatePhoto(MultipartFile image, Integer userId) {
        log.info("Запущен метод сервиса {}", MethodInspector.getCurrentMethodName());

        //ищем юзера в БД, если не найден, то выбрасываем исключение
        User user = userRepository.findById(userId).orElseThrow(
                () -> new PhotoOnDatabaseIsAbsentException("Ошибка: фото отсутствует в базе данных")
        );

        //если у юзера уже есть картинка, то нужно ее удалить
        if (user.getPhoto() != null) {
            photoRepository.delete(user.getPhoto());
        }

        //заполняем поле photo у юзера и сохраняем фото в БД
        Photo photo = photoMapper.mapMuptipartFileToPhoto(image);
        if (photo == null) {
            throw new PhotoWasNotMappedException("Маппинг фото не выполнен");
        }
        user.setPhoto(photo);
        photoRepository.save(photo);

        return photo;
    }
}

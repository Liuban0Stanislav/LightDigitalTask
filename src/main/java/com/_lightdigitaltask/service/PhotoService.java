package com._lightdigitaltask.service;

import com._lightdigitaltask.models.Photo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;

public interface PhotoService {
    byte[] getPhoto(Integer pk) throws IOException, NoSuchFieldException;
    Photo updatePhoto(MultipartFile image, Integer userId) throws IOException;
}

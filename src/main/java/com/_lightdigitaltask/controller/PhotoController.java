package com._lightdigitaltask.controller;

import com._lightdigitaltask.models.Photo;
import com._lightdigitaltask.service.Impl.UserServiceImpl;
import com._lightdigitaltask.service.PhotoService;
import com._lightdigitaltask.service.UserService;
import com._lightdigitaltask.servlet.MethodInspector;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/photo")
public class PhotoController {

    private final PhotoService photoService;

    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @GetMapping("/avatar/{photoId}")
    public ResponseEntity<byte[]> getPhotoFromSource(@PathVariable Integer photoId) throws IOException, NoSuchFieldException {
        log.info("Запущен метод контроллера {}", MethodInspector.getCurrentMethodName());

        if(photoService.getPhoto(photoId) != null){
        return ResponseEntity.ok(photoService.getPhoto(photoId));
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/avatar/{userId}")
    public Photo updatePhoto (@RequestBody MultipartFile image, @PathVariable Integer userId) throws IOException {
        log.info("Запущен метод контроллера {}", MethodInspector.getCurrentMethodName());
        return photoService.updatePhoto(image, userId);
    }
}

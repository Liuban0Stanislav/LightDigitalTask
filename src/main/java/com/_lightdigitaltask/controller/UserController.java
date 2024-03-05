package com._lightdigitaltask.controller;

import com._lightdigitaltask.DTO.UserDTO;
import com._lightdigitaltask.models.User;
import com._lightdigitaltask.service.Impl.UserServiceImpl;
import com._lightdigitaltask.servlet.MethodInspector;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com._lightdigitaltask.servlet.MethodInspector.getCurrentClassName;
import static com._lightdigitaltask.servlet.MethodInspector.getCurrentMethodName;

/**
 * Класс содержащий ендпоинты для работы с сущностями пользователей.
 * @Версия: 1.0
 * @Дата: 04.03.2024
 * @Автор: Станислав Любань
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    /**
     * Метод возвращает список всех юзеров.
     * @return список юзеров
     */
    @GetMapping
    public List<UserDTO> getAllUsers(){
        log.info("вызван метод мапера "+ getCurrentClassName() + ": " + getCurrentMethodName());
        return userService.getAllUsers();
    }

    /**
     * Метод получения юзера по ID.
     * @param userId
     * @return
     */
    @GetMapping("/{userId}")
    public UserDTO getUser(@PathVariable Integer userId){
        log.info("вызван метод мапера "+ getCurrentClassName() + ": " + getCurrentMethodName());
        return userService.getUser(userId);
    }
}

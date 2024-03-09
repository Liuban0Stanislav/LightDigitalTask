package com._lightdigitaltask.controller;

import com._lightdigitaltask.DTO.UserDTO;
import com._lightdigitaltask.models.Role;
import com._lightdigitaltask.models.User;
import com._lightdigitaltask.service.Impl.UserServiceImpl;
import com._lightdigitaltask.servlet.MethodInspector;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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
    @PreAuthorize(value = "hasRole('ADMIN')")
    public List<UserDTO> getAllUsers(){
        log.info("вызван метод мапера "+ getCurrentClassName() + ": " + getCurrentMethodName());
        return userService.getAllUsers();
    }

    /**
     * Метод получения юзера по ID.
     * @param userId
     * @return
     */
    @PreAuthorize(value = "hasRole('ADMIN')")
    @GetMapping("/{userId}")
    public UserDTO getUser(@PathVariable Integer userId){
        log.info("вызван метод мапера "+ getCurrentClassName() + ": " + getCurrentMethodName());
        return userService.getUser(userId);
    }

    /**
     * Метод для изменения роли {@link Role} поьзователя.
     * <ul>Роли могут быть следующими:
     * <li>USER</li>
     * <li>OPERATOR</li>
     * <li>ADMIN</li></ul>
     * Чтобы изменить роль, нужно найти пользователя по его id.
     * @param userId id пользователя
     * @param newRole роль пользователя
     * @return объект {@link UserDTO}
     */
    @PreAuthorize(value = "hasRole('ADMIN')")
    @PatchMapping("/newRole/{userId}/{newRole}")
    public UserDTO changeRole(@PathVariable Integer userId,
                              @PathVariable Role newRole){
        return userService.changeRole(userId, newRole);
    }
}

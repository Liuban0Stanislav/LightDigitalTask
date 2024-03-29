package com._lightdigitaltask.DTO;

import com._lightdigitaltask.models.Role;
import lombok.Data;

@Data
public class RegisterDTO {

    private String username; // логин
    private String password; // пароль
    private String firstName; // имя пользователя
    private String lastName; // фамилия пользователя
    private String phone; // телефон пользователя
    private Role role; // роль пользователя
}

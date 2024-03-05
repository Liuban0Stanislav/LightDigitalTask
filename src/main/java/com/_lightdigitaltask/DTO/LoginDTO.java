package com._lightdigitaltask.DTO;

import lombok.Data;

@Data
public class LoginDTO {

    private String username; // логин
    private String password; // пароль

    public LoginDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public LoginDTO(){
    }
}

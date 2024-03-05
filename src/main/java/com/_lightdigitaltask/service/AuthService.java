package com._lightdigitaltask.service;


import com._lightdigitaltask.DTO.RegisterDTO;

public interface AuthService {
    boolean login(String userName, String password);

    boolean register(RegisterDTO register);
}

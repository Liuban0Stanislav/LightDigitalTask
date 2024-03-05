package com._lightdigitaltask.service;

import com._lightdigitaltask.DTO.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> getAllUsers();
    UserDTO getUser(Integer id);
}

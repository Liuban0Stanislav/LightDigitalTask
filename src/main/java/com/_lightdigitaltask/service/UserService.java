package com._lightdigitaltask.service;

import com._lightdigitaltask.DTO.UserDTO;
import com._lightdigitaltask.models.Role;

import java.util.List;

public interface UserService {
    List<UserDTO> getAllUsers();
    UserDTO getUser(Integer id);
    UserDTO changeRole(Integer userId, Role newRole);
}

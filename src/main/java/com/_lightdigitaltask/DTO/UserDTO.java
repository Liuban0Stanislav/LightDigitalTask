package com._lightdigitaltask.DTO;

import com._lightdigitaltask.models.Application;
import com._lightdigitaltask.models.Photo;
import com._lightdigitaltask.models.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.Collection;

@Data
@AllArgsConstructor
public class UserDTO {

    private Integer id;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private Role role;
    private Photo photo;
}

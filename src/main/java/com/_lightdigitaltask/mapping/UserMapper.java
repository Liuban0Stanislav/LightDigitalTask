package com._lightdigitaltask.mapping;

import com._lightdigitaltask.DTO.RegisterDTO;
import com._lightdigitaltask.DTO.UserDTO;
import com._lightdigitaltask.models.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com._lightdigitaltask.servlet.MethodInspector.getCurrentClassName;
import static com._lightdigitaltask.servlet.MethodInspector.getCurrentMethodName;

/**
 * Класс-маппер, нужен для конвертации объектов {@link User} в {@link UserDTO} и обратно.
 * @Версия: 1.0
 * @Дата: 05.03.2024
 * @Автор: Станислав Любань
 */
@Slf4j
@Component
public class UserMapper {

    /**
     * {@link User} -> {@link UserDTO}
     * @param user {@link User}
     * @return {@link UserDTO}
     */
    public UserDTO mapUserToUserDTO(User user){
        return new UserDTO(
                user.getId(),
                user.getUserName(),
                user.getPassword(),
                user.getFirstName(),
                user.getLastName(),
                user.getPhone(),
                user.getRole(),
                user.getPhoto()
        );
    }

    /**
     * List<{@link User}> -> List<{@link UserDTO}>
     * @param listUser List<{@link User}>
     * @return List<{@link UserDTO}>
     */
    public List<UserDTO> mapAllUsersToAllUsersDto(List<User> listUser) {
        log.info("вызван метод мапера "+ getCurrentClassName() + ": " + getCurrentMethodName());

        List<UserDTO> listUserDTO = new ArrayList<>();

        for (User user: listUser) {
            UserDTO userDTO = new UserDTO(
                    user.getId(),
                    user.getUserName(),
                    user.getPassword(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getPhone(),
                    user.getRole(),
                    user.getPhoto()
            );
            listUserDTO.add(userDTO);
        }
        return listUserDTO;
    }
    public User mapFromRegisterToUser(RegisterDTO register){
        return new User(
                null,
                register.getUsername(),
                register.getPassword(),
                register.getFirstName(),
                register.getLastName(),
                register.getPhone(),
                register.getRole(),
                null
        );
    }
}

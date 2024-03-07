package com._lightdigitaltask.service.Impl;

import com._lightdigitaltask.DTO.UserDTO;
import com._lightdigitaltask.exceptions.UserOnDatabaseIsAbsentException;
import com._lightdigitaltask.mapping.UserMapper;
import com._lightdigitaltask.models.Role;
import com._lightdigitaltask.models.User;
import com._lightdigitaltask.repository.UserRepository;
import com._lightdigitaltask.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com._lightdigitaltask.servlet.MethodInspector.getCurrentClassName;
import static com._lightdigitaltask.servlet.MethodInspector.getCurrentMethodName;

/**
 * Класс-сервис, содержит в себе бизнесс логику, каксающуюся операций с пользователями.
 * @Версия: 1.0
 * @Дата: 05.03.2024
 * @Автор: Станислав Любань
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    /**
     * Метод возвращает всех пользователей из БД.
     *
     * @return список объектов {@link UserDTO}
     */
    @Override
    public List<UserDTO> getAllUsers() {
        log.info("вызван метод мапера "+ getCurrentClassName() + ": " + getCurrentMethodName());

        return userMapper.mapAllUsersToAllUsersDto(userRepository.findAll());
    }

    /**
     * Метод возвращает пользователя из БД по его id.
     * @param id пользователя
     * @return объект {@link UserDTO}
     */
    @Override
    public UserDTO getUser(Integer id) {
        log.info("вызван метод мапера "+ getCurrentClassName() + ": " + getCurrentMethodName());

        UserDTO userDTO = null;
        if (userRepository.findById(id).isPresent()) {
            userDTO = userMapper.mapUserToUserDTO(userRepository.findById(id).orElseThrow(
                    () -> new UserOnDatabaseIsAbsentException("Искомый пользователь не найден в БД."))
            );
        }
        return userDTO;
    }

    /**
     * Метод изменяет роль пользователя.
     * @param userId iid пользователя
     * @param newRole новая роль пользователя
     * @return {@link UserDTO}
     */
    public UserDTO changeRole(Integer userId, Role newRole){
        log.info("вызван метод сервиса " + getCurrentClassName() + ": " + getCurrentMethodName());

        User user = null;
        if (userRepository.findById(userId).isPresent()) {

            //получаем юзера из БД, либо выбрасываем исключение, если юзера там нет.
            user = userRepository.findById(userId).orElseThrow(
                    () -> new UserOnDatabaseIsAbsentException("Искомый пользователь не найден в БД.")
            );

            //меняем роль юзера и сохраняем его обратно в БД
            user.setRole(newRole);
            userRepository.save(user);
        }

        //возвращаем ДТО
        return userMapper.mapUserToUserDTO(user);
    }
}

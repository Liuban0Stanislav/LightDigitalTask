package com._lightdigitaltask.service;

import com._lightdigitaltask.repository.UserRepository;
import com._lightdigitaltask.servlet.MethodInspector;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Класс используется при авторизации для получения пользователя по логину.
 * @Версия: 1.0
 * @Дата: 03.03.2024
 * @Автор: Станислав Любань
 */
@Slf4j
@Service
public class MyUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    public MyUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Метод получает логин пользователя и проверяет есть ли пользователь с таким логином в БД.
     * Если пользователь найден, то из метода возвращается объект класса:
     * <br>org.springframework.security.core.userdetails.User</br>
     * @param - логин userName
     * @return - объект User из пакета org.springframework.security.core.userdetails.
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        log.info("Запущен метод сервиса {}", MethodInspector.getCurrentMethodName());
        com._lightdigitaltask.models.User user = userRepository.findUserByUserName(userName);
        if (user == null) {
            throw new UsernameNotFoundException("Пользователь не найден");
        }else {
            log.info("Пользователь {} найден, авторизация УСПЕШНА.", user.getUserName());
        }
        return User.builder()
                .username(user.getUserName())
                .password(user.getPassword())
                .roles(user.getRole().name())
                .build();
    }
}

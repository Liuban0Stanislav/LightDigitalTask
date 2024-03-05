package com._lightdigitaltask.service.Impl;

import com._lightdigitaltask.DTO.RegisterDTO;
import com._lightdigitaltask.exceptions.UserAlredyExsistException;
import com._lightdigitaltask.exceptions.WrongPasswordException;
import com._lightdigitaltask.mapping.UserMapper;
import com._lightdigitaltask.models.User;
import com._lightdigitaltask.repository.UserRepository;
import com._lightdigitaltask.service.AuthService;
import com._lightdigitaltask.service.MyUserDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com._lightdigitaltask.servlet.MethodInspector.getCurrentClassName;
import static com._lightdigitaltask.servlet.MethodInspector.getCurrentMethodName;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder encoder;
    private final UserRepository userRepository;
    private final MyUserDetailService myUserDetailService;
    private final UserMapper userMapper;

    public AuthServiceImpl(PasswordEncoder passwordEncoder,
                           UserRepository userRepository, MyUserDetailService myUserDetailService, UserMapper userMapper) {
        this.encoder = passwordEncoder;
        this.userRepository = userRepository;
        this.myUserDetailService = myUserDetailService;
        this.userMapper = userMapper;
    }

    /**
     * Метод проверяет наличие пользователя
     * с указанным логином в БД. И если такого пользователя нет, то выбрасывает исключение
     * {@link UsernameNotFoundException}. Если пользователь найден, то он возвращается из метода.
     * <p>Далее метод <b>encoder.matches()</b> сравнивает пароли.
     * Если проверка пройдена, то метод возвращает true, если же нет, то метод выбрасывает исключение
     * {@link WrongPasswordException}.</p>
     *
     * @param userName
     * @param password
     * @return true - если пользователь существует в БД, и пароли совпадают;
     * {@link UsernameNotFoundException} - если пользователь не найден в БД;
     * {@link WrongPasswordException} - если пароли не совпадают.
     */
    @Override
    public boolean login(String userName, String password) {
        log.info("вызван метод контроллера "+ getCurrentClassName() + ": " + getCurrentMethodName());
        UserDetails userDetails = myUserDetailService.loadUserByUsername(userName);
        if (!encoder.matches(password, userDetails.getPassword())) {
            throw new WrongPasswordException("Неверный пароль");
        }
        return true;
    }

    /**
     * Метод регистрирует нового пользователя.
     * <p>Метод получает ДТО {@link RegisterDTO} из контроллера. Далее ДТО конвертируется методом
     * {@link UserMapper#mapFromRegisterToUser(RegisterDTO)} в сущность {@link User}.</p>
     * <p>Если пользователь с таким логином ({@link User#getUserName()}) в репозитории найден,
     * то выбрасывается исключение {@link UserAlredyExsistException}.</p>
     * <p>Если проверка пройдена успешно и такого логина нет в базе данных, то в таком случае
     * пароль кодируется, сохраняется в сущность {@link User}. А сама сущность сохраняется в
     * базе данных.</p>
     *
     * @param register
     * @return true в случае, если пользователь уникален и сохранен в БД;
     * в случае, если проверка на уникальность логина не пройдена, выбрасывается исключение
     * {@link UserAlredyExsistException}.
     */
    @Override
    public boolean register(RegisterDTO register) {
        log.info("вызван метод контроллера "+ getCurrentClassName() + ": " + getCurrentMethodName());
        User user = userMapper.mapFromRegisterToUser(register);
        if (userRepository.findUserByUserName(user.getUserName()) != null) {
            throw new UserAlredyExsistException("Такой пользователь существует");
        }
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }
}

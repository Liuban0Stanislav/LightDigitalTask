package com._lightdigitaltask.repository;

import com._lightdigitaltask.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Класс для осуществления связи между сервисом {@link UserServiceImpl} и БД.
 * @Версия: 1.0
 * @Дата: 03.03.2024
 * @Автор: Станислав Любань
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByUserName(String userName);
}

package com._lightdigitaltask.repository;

import com._lightdigitaltask.models.Application;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Класс для осуществления связи между сервисом
 * {@link com._lightdigitaltask.service.Impl.ApplicationServiceImpl} и БД.
 * @Версия: 1.0
 * @Дата: 03.03.2024
 * @Автор: Станислав Любань
 */
public interface ApplicationRepository extends JpaRepository<Application, Integer> {
//    @Query(value = "", nativeQuery = true)
//    List<Application> getApplicationsByDate();
}

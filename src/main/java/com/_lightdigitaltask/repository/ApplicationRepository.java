package com._lightdigitaltask.repository;

import com._lightdigitaltask.models.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Класс для осуществления связи между сервисом
 * {@link com._lightdigitaltask.service.Impl.ApplicationServiceImpl} и БД.
 * @Версия: 1.0
 * @Дата: 03.03.2024
 * @Автор: Станислав Любань
 */
public interface ApplicationRepository extends JpaRepository<Application, Integer> {

    @Query(value = "SELECT * FROM (SELECT * FROM application AS a WHERE status = :stat) AS a ORDER BY a.date DESC OFFSET :page ROWS FETCH NEXT :size ROWS ONLY;", nativeQuery = true)
    List<Application> getApplicationsByDateDecreaseOrderAccordingToStatus(int stat, int page, int size);

    @Query(value = "SELECT * FROM (SELECT * FROM application AS a WHERE status = :stat) AS a ORDER BY a.date ASC OFFSET :page ROWS FETCH NEXT :size ROWS ONLY;", nativeQuery = true)
    List<Application> getApplicationsByDateIncreaseOrderAccordingToStatus(int stat, int page, int size);
}

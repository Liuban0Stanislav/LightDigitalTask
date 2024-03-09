package com._lightdigitaltask.repository;

import com._lightdigitaltask.DTO.ApplicationDTOproj;
import com._lightdigitaltask.models.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Класс для осуществления связи между сервисом
 * {@link com._lightdigitaltask.service.Impl.ApplicationServiceImpl} и БД.
 *
 * @Версия: 1.0
 * @Дата: 03.03.2024
 * @Автор: Станислав Любань
 */
public interface ApplicationRepository extends JpaRepository<Application, Integer> {
    @Query(value = "SELECT * " +
            "FROM (SELECT * " +
            "      FROM application AS a " +
            "               JOIN users AS u ON a.users_id = u.id " +
            "      WHERE u.role = 0 " +
            "        AND a.status = :stat) AS a " +
            "WHERE a.users_id = :currentUserId " +
            "ORDER BY CASE " +
            "             WHEN :sortOrder = 'ASC' THEN a.date END ASC, " +
            "         CASE " +
            "             WHEN :sortOrder = 'DESC' THEN a.date END DESC " +
            "OFFSET :page ROWS FETCH NEXT :size ROWS ONLY;", nativeQuery = true)
    List<Application> getApplicationsByUserFilters(int stat, int page, int size, String sortOrder, int currentUserId);

    /**
     * Метод для оператора. Позволяет смотреть все отправленные на рассмотрение заявки с возможностью
     * сортировки по дате создания в оба направления (как от самой старой к самой
     * новой, так и наоборот) и пагинацией по 5 элементов. Есть фильтрация по имени.
     * @param login
     * @return
     */
//    @Query(value = "SELECT " +
//            "    a.id, " +
//            "    a.text, " +
//            "    a.date, " +
//            "    a.status, " +
//            "    a.phone, " +
//            "    a.user_name, " +
//            "    a.users_id " +
//            "FROM (SELECT * FROM application AS ap " +
//            "               WHERE ap.status = 1) AS a " +
//            "         JOIN users AS u ON a.users_id = u.id " +
//            "WHERE u.first_name LIKE :login " +
//            "ORDER BY CASE " +
//            "             WHEN :sortOrder = 'ASC' THEN a.date END ASC, " +
//            "         CASE " +
//            "             WHEN :sortOrder = 'DESC' THEN a.date END DESC " +
//            "LIMIT 5 OFFSET :page", nativeQuery = true)

    @Query(value = "SELECT " +
            "    a.id, " +
            "    a.text, " +
            "    a.date, " +
            "    a.status, " +
            "    a.phone, " +
            "    a.user_name, " +
            "    a.users_id " +
            "FROM application AS a " +
            "         JOIN users AS u ON a.users_id = u.id " +
            "WHERE u.user_name LIKE :login " +
            "ORDER BY CASE " +
            "             WHEN :sortOrder = 'ASC' THEN a.date END ASC, " +
            "         CASE " +
            "             WHEN :sortOrder = 'DESC' THEN a.date END DESC " +
            "LIMIT 5 OFFSET :page", nativeQuery = true)
    List<ApplicationDTOproj> getApplicationsByOperatorFirstFilters(int page, String login, String sortOrder);

    /**
     * Метод для оператора. Позволяет просматривать отправленные заявки
     * только конкретного пользователя по его имени/части имени.
     * @param userName
     * @return
     */
    @Query(value = "SELECT " +
            "    a.id AS id, " +
            "    a.status AS status, " +
            "    a.text AS text, " +
            "    a.date AS date, " +
            "    a.phone AS phone, " +
            "    a.users_id AS userId " +
            "FROM application AS a " +
            "         JOIN users AS u ON a.users_id = u.id " +
            "WHERE u.first_name ILIKE '%' || :userName || '%' " +
            "ORDER BY CASE " +
            "             WHEN :sortOrder = 'ASC' THEN a.date END ASC, " +
            "         CASE " +
            "             WHEN :sortOrder = 'DESC' THEN a.date END DESC " +
            "LIMIT 5 OFFSET 0;", nativeQuery = true)
    List<ApplicationDTOproj> getApplicationsByOperatorSecondFilters(String userName, String sortOrder);

    @Query(value = "SELECT a.id, " +
            "       a.text, " +
            "       a.date, " +
            "       a.status, " +
            "       a.phone, " +
            "       a.user_name, " +
            "       a.users_id " +
            "FROM application AS a " +
            "         JOIN users AS u ON a.users_id = u.id " +
            "WHERE u.user_name LIKE :userName " +
            "    AND a.status IN (1, 2, 3) " +
            "ORDER BY a.date DESC " +
            "LIMIT 5 OFFSET :page", nativeQuery = true)
    List<ApplicationDTOproj> getApplicationsByAdminFilters(int page, String userName);
}

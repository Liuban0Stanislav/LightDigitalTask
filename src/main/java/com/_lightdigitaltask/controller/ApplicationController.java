package com._lightdigitaltask.controller;

import com._lightdigitaltask.DTO.ApplicationDTOproj;
import com._lightdigitaltask.DTO.ApplicationInDTO;
import com._lightdigitaltask.DTO.ApplicationOutDTO;
import com._lightdigitaltask.service.ApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com._lightdigitaltask.servlet.MethodInspector.getCurrentClassName;
import static com._lightdigitaltask.servlet.MethodInspector.getCurrentMethodName;

/**
 * Класс реализует бизнесс логику для выполнения операций с заявками.
 * @Версия: 1.0
 * @Дата: 05.03.2024
 * @Автор: Станислав Любань
 */
@Slf4j
@RestController
@RequestMapping("/application")
public class ApplicationController {
    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    /**
     * Метод для создания заявки.
     * @param applicationIn
     * @return
     */
    @PreAuthorize(value = "hasRole('USER')")
    @PutMapping
    public ApplicationOutDTO createApplication(@RequestBody ApplicationInDTO applicationIn) {
        log.info("вызван метод контроллера " + getCurrentClassName() + ": " + getCurrentMethodName());
        return applicationService.createApplication(applicationIn);
    }

    /**
     * Метод для просмотра всех заявок всех пользователей.
     * @return список заявок
     */
    @PreAuthorize(value = "hasRole('ADMIN')")
    @GetMapping
    public List<ApplicationOutDTO> getAllApplications() {
        log.info("вызван метод контроллера " + getCurrentClassName() + ": " + getCurrentMethodName());
        return applicationService.getAllApplications();
    }

    /**
     * Метод пользователя для просмотра созданных им заявки с возможностью сортировки по дате
     * создания в оба направления (как от самой старой к самой новой, так и наоборот)
     * и пагинацией по 5 элементов.
     * @param stat статус заявки
     * @param page просматриваемая страница
     * @param size размер страницы
     * @param sortOrder порядок сортировки (по возрастанию/по убыванию)
     * @param auth отсюда берется логин текущего пользователя
     * @return список заявок
     */
    @PreAuthorize(value = "hasRole('ADMIN') or hasRole('OPERATOR')")
    @GetMapping("/getUserApplications")
    public List<ApplicationOutDTO> getApplicationsByUserFilters(
            @RequestParam String stat,
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String sortOrder,
            Authentication auth) {
        log.info("вызван метод контроллера " + getCurrentClassName() + ": " + getCurrentMethodName());

        return applicationService.getApplicationsByUserFilters(stat, page, size, sortOrder, auth);
    }

    /**
     * Метод оператора, который позволяет смотреть все отправленные на рассмотрение заявки с возможностью
     * сортировки по дате создания в оба направления (как от самой старой к самой
     * новой, так и наоборот) и пагинацией по 5 элементов. Должна быть фильтрация по имени.
     * @param page номер просматриваемой страницы
     * @param login логин искомого пользователя
     * @param sortOrder порядок сортировки (по возрастанию/по убыванию)
     * @return список заявок
     */
    @PreAuthorize(value = "hasRole('ADMIN') or hasRole('OPERATOR')")
    @GetMapping("/getOperatorFirstListApplications")
    public List<ApplicationDTOproj> getApplicationsByOperatorFirstFilters(
            @RequestParam int page,
            @RequestParam String login,
            @RequestParam String sortOrder) {
        log.info("вызван метод контроллера " + getCurrentClassName() + ": " + getCurrentMethodName());

        return applicationService.getApplicationsByOperatorFirstFilters(page, login, sortOrder);
    }

    /**
     * Метод позволяет просматривать отправленные заявки только конкретного пользователя по его
     * имени/части имени
     * @param userName логин искомого пользователя
     * @param sortOrder порядок сортировки (по возрастанию/по убыванию)
     * @return
     */
    @PreAuthorize(value = "hasRole('ADMIN') or hasRole('OPERATOR')")
    @GetMapping("/getOperatorSecondListApplications")
    public List<ApplicationDTOproj> getApplicationsByOperatorSecondFilters(
            @RequestParam String userName,
            @RequestParam String sortOrder) {
        log.info("вызван метод контроллера " + getCurrentClassName() + ": " + getCurrentMethodName());

        return applicationService.getApplicationsByOperatorSecondFilters(userName, sortOrder);
    }

    /**
     * Метод позволяет смотреть заявки в статусе отправлено, принято, отклонено. Пагинация 5 элементов, сортировка по дате.
     * Фильтрация по имени.
     * @param page номер просматриваемой страницы
     * @param userName логин искомого пользователя
     * @return список заявок
     */
    @PreAuthorize(value = "hasRole('ADMIN')")
    @GetMapping("/getAdminListApplications")
    public List<ApplicationDTOproj> getApplicationsByAdminFilters(
            @RequestParam int page,
            @RequestParam String userName) {
        log.info("вызван метод контроллера " + getCurrentClassName() + ": " + getCurrentMethodName());

        return applicationService.getApplicationsByAdminFilters(page, userName);
    }
}

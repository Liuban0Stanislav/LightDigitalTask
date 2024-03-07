package com._lightdigitaltask.service;

import com._lightdigitaltask.DTO.ApplicationInDTO;
import com._lightdigitaltask.DTO.ApplicationOutDTO;
import com._lightdigitaltask.models.Application;
import com._lightdigitaltask.models.Status;

import java.util.List;

public interface ApplicationService {
    /**
     * Метод для создания заявки.
     * @param applicationIn {@link ApplicationInDTO}
     * @return {@link ApplicationOutDTO}
     */
    ApplicationOutDTO createApplication(ApplicationInDTO applicationIn);

    /**
     * Метод обновляет статус заявки при рассмотуурении ее оператором.
     * @param applicationId id заявки
     * @param newStatus новый статус завки
     * @return {@link ApplicationOutDTO}
     */
    ApplicationOutDTO updateStatus(Integer applicationId, Status newStatus);

    /**
     * Метод возвращает список всех заявок.
     * @return список заявок
     */
    List<ApplicationOutDTO> getAllApplications();

    /**
     * Метод возвращает список заявок с возможностью сортировки по дате от большего к меньшему и пагинацией
     * по 5 элементов, фильтрация по статусу
     * @param stat статус заявки
     * @param page номер страницы
     * @param size количество записоей на странице
     * @return {@link ApplicationOutDTO}
     */
    List<ApplicationOutDTO> getApplicationsByDateDecreaseOrderAccordingToStatus(String stat, int page, int size);
    /**
     * Метод возвращает список заявок с возможностью сортировки по дате от меньшего к большему и пагинацией
     * по 5 элементов, фильтрация по статусу
     * @param stat статус заявки
     * @param page номер страницы
     * @param size количество записоей на странице
     * @return {@link ApplicationOutDTO}
     */
    List<ApplicationOutDTO> getApplicationsByDateIncreaseOrderAccordingToStatus(String stat, int page, int size);

    /**
     * Метод возвращает завяку по ее id
     * @param applicationId id заявки
     * @return {@link ApplicationOutDTO}
     */
    ApplicationOutDTO getApplication(int applicationId);
}

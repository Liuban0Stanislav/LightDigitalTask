package com._lightdigitaltask.service;

import com._lightdigitaltask.DTO.ApplicationDTO;
import com._lightdigitaltask.models.Application;
import com._lightdigitaltask.models.Status;

import java.util.List;

public interface ApplicationService {
    /**
     * Метод для создания заявки.
     * @param application
     * @return application
     */
    Application createApplication(Application application);

    /**
     * Метод обновляет статус заявки при рассмотуурении ее оператором
     * @param applicationId, status
     * @return application
     */
    Application updateStatus(Integer applicationId, Status newStatus);

    /**
     * Посмотреть список заявок
     * @return список заявок
     */
    List<ApplicationDTO> getAllApplications();
}

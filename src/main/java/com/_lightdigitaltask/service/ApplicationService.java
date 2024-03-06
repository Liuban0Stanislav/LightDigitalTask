package com._lightdigitaltask.service;

import com._lightdigitaltask.DTO.ApplicationInDTO;
import com._lightdigitaltask.DTO.ApplicationOutDTO;
import com._lightdigitaltask.models.Application;
import com._lightdigitaltask.models.Status;

import java.util.List;

public interface ApplicationService {
    /**
     * Метод для создания заявки.
     * @param application
     * @return application
     */
    ApplicationOutDTO createApplication(ApplicationInDTO applicationIn);

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
    List<ApplicationOutDTO> getAllApplications();
    List<ApplicationOutDTO> getApplicationsByDateDecreaseOrderAccordingToStatus(String stat, int page, int size);
    List<ApplicationOutDTO> getApplicationsByDateIncreaseOrderAccordingToStatus(String stat, int page, int size);
}

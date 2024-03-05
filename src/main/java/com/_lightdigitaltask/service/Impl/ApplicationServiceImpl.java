package com._lightdigitaltask.service.Impl;

import com._lightdigitaltask.DTO.ApplicationDTO;
import com._lightdigitaltask.exceptions.ApplicationOnDatabaseIsAbsentException;
import com._lightdigitaltask.mapping.ApplicationMapper;
import com._lightdigitaltask.models.Application;
import com._lightdigitaltask.models.Status;
import com._lightdigitaltask.repository.ApplicationRepository;
import com._lightdigitaltask.service.ApplicationService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Класс-сервис для выполнения операций с заявками.
 *
 * @Версия: 1.0
 * @Дата: 05.03.2024
 * @Автор: Станислав Любань
 */
@Service
public class ApplicationServiceImpl implements ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final ApplicationMapper applicationMapper;

    public ApplicationServiceImpl(ApplicationRepository applicationRepository, ApplicationMapper applicationMapper) {
        this.applicationRepository = applicationRepository;
        this.applicationMapper = applicationMapper;
    }

    @Override
    public Application createApplication(Application application) {
//todo сделать привязку имени и телефона к юзеру
        return applicationRepository.save(application);
    }

    @Override
    public Application updateStatus(Integer applicationId, Status newStatus) {
        Application application;
        if (applicationRepository.findById(applicationId).isPresent()) {
            application = applicationRepository.findById(applicationId).get();
            application.setStatus(newStatus);
            return applicationRepository.save(application);
        } else {
            throw new ApplicationOnDatabaseIsAbsentException("Заявление с таким ID отсутствует в БД.");
        }
    }

    @Override
    public List<ApplicationDTO> getAllApplications() {
        return applicationMapper.mapAllApplicationsToAllApplicationDto(applicationRepository.findAll());
    }
}

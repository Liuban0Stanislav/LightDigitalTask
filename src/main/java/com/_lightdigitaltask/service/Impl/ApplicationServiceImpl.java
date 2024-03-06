package com._lightdigitaltask.service.Impl;

import com._lightdigitaltask.DTO.ApplicationInDTO;
import com._lightdigitaltask.DTO.ApplicationOutDTO;
import com._lightdigitaltask.exceptions.ApplicationOnDatabaseIsAbsentException;
import com._lightdigitaltask.mapping.ApplicationMapper;
import com._lightdigitaltask.models.Application;
import com._lightdigitaltask.models.Status;
import com._lightdigitaltask.repository.ApplicationRepository;
import com._lightdigitaltask.service.ApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static com._lightdigitaltask.servlet.MethodInspector.getCurrentClassName;
import static com._lightdigitaltask.servlet.MethodInspector.getCurrentMethodName;

/**
 * Класс-сервис для выполнения операций с заявками.
 *
 * @Версия: 1.0
 * @Дата: 05.03.2024
 * @Автор: Станислав Любань
 */
@Slf4j
@Service
public class ApplicationServiceImpl implements ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final ApplicationMapper applicationMapper;

    public ApplicationServiceImpl(ApplicationRepository applicationRepository, ApplicationMapper applicationMapper) {
        this.applicationRepository = applicationRepository;
        this.applicationMapper = applicationMapper;
    }

    @Override
    public ApplicationOutDTO createApplication(ApplicationInDTO applicationIn) {
        log.info("вызван метод сервиса " + getCurrentClassName() + ": " + getCurrentMethodName());

        //маппим входящее ДТО в объект заявки
        Application application = applicationMapper.mapApplicationInDtoToApplication(applicationIn);

        //заявку сохраняем в БД, а вовращаемый результат мапим в "выходное" ДТО
        return applicationMapper.mapApplicationToApplicationOutDTO(applicationRepository.save(application));
    }

    @Override
    public Application updateStatus(Integer applicationId, Status newStatus) {
        log.info("вызван метод сервиса " + getCurrentClassName() + ": " + getCurrentMethodName());

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
    public List<ApplicationOutDTO> getAllApplications() {
        log.info("вызван метод сервиса " + getCurrentClassName() + ": " + getCurrentMethodName());
        return applicationMapper.mapAllApplicationsToAllApplicationDto(applicationRepository.findAll());
    }


    @Transactional
    public List<ApplicationOutDTO> getApplicationsByDateDecreaseOrderAccordingToStatus(String stat, int page, int size) {
        log.info("вызван метод сервиса " + getCurrentClassName() + ": " + getCurrentMethodName());

        //Конвертируем статтус из строкового формата в число для БД
        var mapedStat = this.mapStatus(stat);

        //Поиск в БД списка заявок с сортировкой по датам в завосимости от их статуса.
        return applicationMapper.mapAllApplicationsToAllApplicationDto(
                applicationRepository.getApplicationsByDateDecreaseOrderAccordingToStatus(mapedStat, page, size)
        );
    }

    @Transactional
    public List<ApplicationOutDTO> getApplicationsByDateIncreaseOrderAccordingToStatus(String stat, int page, int size) {
        log.info("вызван метод сервиса " + getCurrentClassName() + ": " + getCurrentMethodName());

        //Конвертируем статтус из строкового формата в число для БД
        var mapedStat = this.mapStatus(stat);

        //Поиск в БД списка заявок с сортировкой по датам в завосимости от их статуса.
        return applicationMapper.mapAllApplicationsToAllApplicationDto(
                applicationRepository.getApplicationsByDateIncreaseOrderAccordingToStatus(mapedStat, page, size)
        );
    }
    /**
     * Метод для конвертации статуса заявки из строкового формата в цифровой (принимается в БД).
     *
     * @param stat
     * @return int
     */
    private int mapStatus(String stat) {
        int rez;
        switch (stat) {
            case "DRAFT":
                rez = 0;
                break;
            case "SENT":
                rez = 1;
                break;
            case "ACCEPTED":
                rez = 2;
                break;
            case "REJECTED":
                rez = 3;
                break;
            default:
                rez = -1;
                break;
        }
        if (rez == -1) {
            throw new RuntimeException("Статус введен не верно, проверьте правильность написания.");
        }
        return rez;
    }
}

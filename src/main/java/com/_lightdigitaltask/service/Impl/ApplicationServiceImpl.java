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

import static com._lightdigitaltask.models.Status.DRAFT;
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

    /**
     * Метод создает заявку.
     * <br>Т.к. в объекте заявки есть поля "телефон юзера" и "имя юзера",
     * то эти данные в маппере подтягиваются из сущности авторизованного юзера.
     * То есть именно того, который создает текущую завку.</br>
     * @param applicationIn {@link ApplicationInDTO}
     * @return {@link ApplicationOutDTO}
     */
    @Override
    public ApplicationOutDTO createApplication(ApplicationInDTO applicationIn) {
        log.info("вызван метод сервиса " + getCurrentClassName() + ": " + getCurrentMethodName());

        //маппим входящее ДТО в объект заявки
        Application application = applicationMapper.mapApplicationInDtoToApplication(applicationIn);

        //вновь созданная заявка всегда черновик.
        application.setStatus(DRAFT);

        //заявку сохраняем в БД, а вовращаемый результат мапим в "выходное" ДТО
        return applicationMapper.mapApplicationToApplicationOutDTO(applicationRepository.save(application));
    }

    /**
     * Метод обновляет статус завки.
     * @param applicationId id заявки
     * @param newStatus новый статус
     * @return {@link ApplicationOutDTO}
     */
    @Override
    public ApplicationOutDTO updateStatus(Integer applicationId, Status newStatus) {
        log.info("вызван метод сервиса " + getCurrentClassName() + ": " + getCurrentMethodName());

        Application application = null;
        if (applicationRepository.findById(applicationId).isPresent()) {
            application = applicationRepository.findById(applicationId).orElseThrow(
                    () -> new ApplicationOnDatabaseIsAbsentException("Заявление с таким ID отсутствует в БД.")
            );
            application.setStatus(newStatus);
        }
        return applicationMapper.mapApplicationToApplicationOutDTO(applicationRepository.save(application));
    }

    /**
     * Метод возвращает список всех заявок.
     * @return список заявок
     */
    @Override
    public List<ApplicationOutDTO> getAllApplications() {
        log.info("вызван метод сервиса " + getCurrentClassName() + ": " + getCurrentMethodName());
        return applicationMapper.mapAllApplicationsToAllApplicationDto(applicationRepository.findAll());
    }

    /**
     * Метод возвращает список заявок с возможностью сортировки по дате от большего к меньшему и пагинацией
     * по 5 элементов, фильтрация по статусу
     * @param stat статус заявки
     * @param page номер страницы
     * @param size количество записоей на странице
     * @return {@link ApplicationOutDTO}
     */
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

    /**
     * Метод возвращает список заявок с возможностью сортировки по дате от меньшего к большему и пагинацией
     * по 5 элементов, фильтрация по статусу
     * @param stat статус заявки
     * @param page номер страницы
     * @param size количество записоей на странице
     * @return {@link ApplicationOutDTO}
     */
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
     * @param stat статус в строковом формате
     * @return int статус в числовом формате
     */
    private int mapStatus(String stat) {
        log.info("вызван метод сервиса " + getCurrentClassName() + ": " + getCurrentMethodName());
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

    /**
     * Метод возвращает завяку по ее id
     * @param applicationId id заявки
     * @return {@link ApplicationOutDTO}
     */
    public ApplicationOutDTO getApplication(int applicationId){
        log.info("вызван метод сервиса " + getCurrentClassName() + ": " + getCurrentMethodName());

        Application application = null;
        if (applicationRepository.findById(applicationId).isPresent()) {
            application = applicationRepository.findById(applicationId).orElseThrow(
                    () -> new ApplicationOnDatabaseIsAbsentException("Заявления с таким id нет в базе данных.")
            );
        }
        return applicationMapper.mapApplicationToApplicationOutDTO(application);
    }
}

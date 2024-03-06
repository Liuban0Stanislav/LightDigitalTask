package com._lightdigitaltask.mapping;

import com._lightdigitaltask.DTO.ApplicationInDTO;
import com._lightdigitaltask.DTO.ApplicationOutDTO;
import com._lightdigitaltask.models.Application;
import com._lightdigitaltask.models.User;
import com._lightdigitaltask.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com._lightdigitaltask.servlet.MethodInspector.getCurrentClassName;
import static com._lightdigitaltask.servlet.MethodInspector.getCurrentMethodName;

/**
 * Класс-маппер, нужен для конвертации объектов {@link Application} в
 * {@link ApplicationOutDTO} и обратно.
 * @Версия: 1.0
 * @Дата: 05.03.2024
 * @Автор: Станислав Любань
 */
@Slf4j
@Component
public class ApplicationMapper {
    private final UserRepository userRepository;

    public ApplicationMapper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * {@link Application} -> {@link ApplicationOutDTO}
     * @param application {@link Application}
     * @return {@link ApplicationOutDTO}
     */
    public ApplicationOutDTO mapApplicationToApplicationOutDTO(Application application){
        return new ApplicationOutDTO(
                application.getId(),
                application.getStatus(),
                application.getText(),
                application.getDate(),
                application.getUsers().getPhone(),
                application.getUsers().getUserName()
        );
    }

    /**
     * {@link ApplicationInDTO} -> {@link Application}
     * @param applicationIn {@link Application}
     * @return {@link ApplicationOutDTO}
     */
    public Application mapApplicationInDtoToApplication(ApplicationInDTO applicationIn){
        log.info("вызван метод мапера "+ getCurrentClassName() + ": " + getCurrentMethodName());

        //находим автора завяления по его Id
        //автор не может быть null? т.к. это пользователь авторизованный во время данной сессии
        int userId = applicationIn.getUserId();
        User user = userRepository.findById(userId).get();

        return new Application(
                null,
                applicationIn.getStatus(),
                applicationIn.getText(),
                applicationIn.getDate(),
                user.getPhone(),
                user.getUserName(),
                user
        );
    }

    /**
     * List<{@link Application}> -> List<{@link ApplicationOutDTO}>
     * @param listApplications List<{@link Application}>
     * @return List<{@link ApplicationDTO}>
     */
    public List<ApplicationOutDTO> mapAllApplicationsToAllApplicationDto(List<Application> listApplications) {
        log.info("вызван метод мапера "+ getCurrentClassName() + ": " + getCurrentMethodName());

        List<ApplicationOutDTO> listApplicationOutDTO = new ArrayList<>();

        for (Application application: listApplications) {
            ApplicationOutDTO applicationOutDTO = this.mapApplicationToApplicationOutDTO(application);
            listApplicationOutDTO.add(applicationOutDTO);
        }
        return listApplicationOutDTO;
    }
}

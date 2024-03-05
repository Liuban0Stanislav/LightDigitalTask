package com._lightdigitaltask.mapping;

import com._lightdigitaltask.DTO.ApplicationDTO;
import com._lightdigitaltask.DTO.UserDTO;
import com._lightdigitaltask.exceptions.MultipartFileToPhotoException;
import com._lightdigitaltask.models.Application;
import com._lightdigitaltask.models.Photo;
import com._lightdigitaltask.models.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com._lightdigitaltask.servlet.MethodInspector.getCurrentClassName;
import static com._lightdigitaltask.servlet.MethodInspector.getCurrentMethodName;

/**
 * Класс-маппер, нужен для конвертации объектов {@link Application} в
 * {@link ApplicationDTO} и обратно.
 * @Версия: 1.0
 * @Дата: 05.03.2024
 * @Автор: Станислав Любань
 */
@Slf4j
@Component
public class ApplicationMapper {
    /**
     * {@link Application} -> {@link ApplicationDTO}
     * @param application {@link Application}
     * @return {@link ApplicationDTO}
     */
    public ApplicationDTO mapApplicationToApplicationDTO(Application application){
        return new ApplicationDTO(
                application.getId(),
                application.getStatus(),
                application.getText(),
                application.getDate(),
                application.getPhone(),
                application.getUserName()
        );
    }

    /**
     * List<{@link Application}> -> List<{@link ApplicationDTO}>
     * @param listApplications List<{@link Application}>
     * @return List<{@link ApplicationDTO}>
     */
    public List<ApplicationDTO> mapAllApplicationsToAllApplicationDto(List<Application> listApplications) {
        log.info("вызван метод мапера "+ getCurrentClassName() + ": " + getCurrentMethodName());

        List<ApplicationDTO> listApplicationDTO = new ArrayList<>();

        for (Application application: listApplications) {
            ApplicationDTO applicationDTO = new ApplicationDTO(
                    application.getId(),
                    application.getStatus(),
                    application.getText(),
                    application.getDate(),
                    application.getPhone(),
                    application.getUserName()
            );
            listApplicationDTO.add(applicationDTO);
        }
        return listApplicationDTO;
    }
}

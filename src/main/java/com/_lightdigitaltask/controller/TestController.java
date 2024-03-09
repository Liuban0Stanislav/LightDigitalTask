package com._lightdigitaltask.controller;

import com._lightdigitaltask.DTO.ApplicationOutDTO;
import com._lightdigitaltask.mapping.ApplicationMapper;
import com._lightdigitaltask.models.Application;
import com._lightdigitaltask.repository.ApplicationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {
    private final ApplicationRepository ar;
    private final ApplicationMapper am;

    public TestController(ApplicationRepository ar, ApplicationMapper am) {
        this.ar = ar;
        this.am = am;
    }

    @GetMapping("/{userName}")
    public List<ApplicationOutDTO> getOperatorQuery(
            @PathVariable String userName) {

        log.info("userName - {}", userName);
        userName = "'%" + userName + "%'";
        log.info("userName - {}", userName);

        List<Application> ap = ar.getApplicationsByOperatorFilters(userName);
        log.info("ApplicationList: \n{}", ap);
        return am.mapAllApplicationsToAllApplicationDto(ap);
    }
}

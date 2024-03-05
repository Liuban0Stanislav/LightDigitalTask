package com._lightdigitaltask.controller;

import com._lightdigitaltask.DTO.ApplicationDTO;
import com._lightdigitaltask.models.Application;
import com._lightdigitaltask.service.ApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com._lightdigitaltask.servlet.MethodInspector.getCurrentClassName;
import static com._lightdigitaltask.servlet.MethodInspector.getCurrentMethodName;

@Slf4j
@RestController
@RequestMapping("/application")
public class ApplicationController {
    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping
    public List<ApplicationDTO> getAllApplications(){
        log.info("вызван метод контроллера "+ getCurrentClassName() + ": " + getCurrentMethodName());
        return applicationService.getAllApplications();
    }
}

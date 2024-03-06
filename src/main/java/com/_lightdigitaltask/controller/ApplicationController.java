package com._lightdigitaltask.controller;

import com._lightdigitaltask.DTO.ApplicationInDTO;
import com._lightdigitaltask.DTO.ApplicationOutDTO;
import com._lightdigitaltask.models.Application;
import com._lightdigitaltask.service.ApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
    @PutMapping
    public ApplicationOutDTO createApplication(@RequestBody ApplicationInDTO applicationIn){
        log.info("вызван метод контроллера "+ getCurrentClassName() + ": " + getCurrentMethodName());
        return applicationService.createApplication(applicationIn);
    }

    @GetMapping
    public List<ApplicationOutDTO> getAllApplications(){
        log.info("вызван метод контроллера "+ getCurrentClassName() + ": " + getCurrentMethodName());
        return applicationService.getAllApplications();
    }

    @GetMapping("/pagingBackOrder/{stat}/{page}/{size}")
    public List<ApplicationOutDTO> getApplicationsByDateDecreaseOrderAccordingToStatus(@PathVariable String stat,
                                                      @PathVariable int page,
                                                      @PathVariable int size){
        log.info("вызван метод контроллера "+ getCurrentClassName() + ": " + getCurrentMethodName());

        return applicationService.getApplicationsByDateDecreaseOrderAccordingToStatus(stat, page, size);
    }
    @GetMapping("/pagingIncreaseOrder/{stat}/{page}/{size}")
    public List<ApplicationOutDTO> getApplicationsByDateIncreaseOrderAccordingToStatus(@PathVariable String stat,
                                                                                       @PathVariable int page,
                                                                                       @PathVariable int size){
        log.info("вызван метод контроллера "+ getCurrentClassName() + ": " + getCurrentMethodName());

        return applicationService.getApplicationsByDateIncreaseOrderAccordingToStatus(stat, page, size);
    }
}

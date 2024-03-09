package com._lightdigitaltask.DTO;

import com._lightdigitaltask.models.Status;

import java.util.Date;

public interface ApplicationDTOproj {
    Integer getId();
    Status getStatus();
    String getText();
    Date getDate();
    String getPhone();
    String getUserName();
    Integer getUsers();
}

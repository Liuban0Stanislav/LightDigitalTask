package com._lightdigitaltask.DTO;

import com._lightdigitaltask.models.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.Date;

@Data
@AllArgsConstructor
public class ApplicationInDTO {
    private Integer id;
    private Status status;
    private String text;
    private Date date;
    private String phone;
    private Integer userId;
}

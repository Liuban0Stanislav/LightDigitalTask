package com._lightdigitaltask.models;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "application")
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Status status;
    private String text;
    private Date date;
    private String phone;
    private String userName;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private User users;

}

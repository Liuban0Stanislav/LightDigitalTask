package com._lightdigitaltask;

import com._lightdigitaltask.utils.JwtTokenUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.userdetails.UserDetails;

@SpringBootApplication
public class LightDigitalWebApp {

	public static void main(String[] args) {
		SpringApplication.run(LightDigitalWebApp.class, args);
	}

}

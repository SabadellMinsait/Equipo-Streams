package com.minsait.consultas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ConsultasApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsultasApplication.class, args);
	}

}

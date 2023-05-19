package com.nyayas.causelist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.nyayas.*")
public class CauseListServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CauseListServiceApplication.class, args);
	}

}

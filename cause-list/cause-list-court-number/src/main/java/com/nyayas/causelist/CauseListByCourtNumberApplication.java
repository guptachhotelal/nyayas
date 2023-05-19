package com.nyayas.causelist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.nyayas.*")
public class CauseListByCourtNumberApplication {

	public static void main(String[] args) {
		SpringApplication.run(CauseListByCourtNumberApplication.class, args);
	}

}

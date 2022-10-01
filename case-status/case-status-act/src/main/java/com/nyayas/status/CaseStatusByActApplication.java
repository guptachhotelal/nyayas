package com.nyayas.status;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class CaseStatusByActApplication {

	public static void main(String[] args) {
		SpringApplication.run(CaseStatusByActApplication.class, args);
	}
}

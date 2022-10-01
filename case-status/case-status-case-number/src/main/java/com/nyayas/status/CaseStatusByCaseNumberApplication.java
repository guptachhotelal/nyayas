package com.nyayas.status;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class CaseStatusByCaseNumberApplication {

	public static void main(String[] args) {
		SpringApplication.run(CaseStatusByCaseNumberApplication.class, args);
	}
}

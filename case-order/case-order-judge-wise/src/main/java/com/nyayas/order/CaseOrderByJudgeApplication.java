package com.nyayas.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class CaseOrderByJudgeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CaseOrderByJudgeApplication.class, args);
	}
}

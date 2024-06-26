package com.nyayas.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "com.nyayas.*")
public class CaseOrderByCaseNumberApplication {

    public static void main(String[] args) {
	SpringApplication.run(CaseOrderByCaseNumberApplication.class, args);
    }
}

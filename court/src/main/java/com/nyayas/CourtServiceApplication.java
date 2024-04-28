package com.nyayas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.builder.SpringApplicationBuilder;
//import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "com.nyayas.*")
public class CourtServiceApplication {

    public static void main(String[] args) {
	SpringApplication.run(CourtServiceApplication.class, args);
    }
}

package com.nyayas.displayboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "com.nyayas.*")
public class DisplayBoardServiceApplication {

    public static void main(String[] args) {
	SpringApplication.run(DisplayBoardServiceApplication.class, args);
    }

}

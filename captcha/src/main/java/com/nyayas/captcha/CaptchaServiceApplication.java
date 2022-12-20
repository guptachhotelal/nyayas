package com.nyayas.captcha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class CaptchaServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CaptchaServiceApplication.class, args);
	}
}

package com.nyayas.captcha.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;

@Component
public class CaptchaServiceFactory {

	@Resource
	private List<CaptchaService> captchServices;

	public CaptchaService getService(Class<CaptchaService> clazz, Object id) {
		return Optional.ofNullable(captchServices)
				.orElseThrow(() -> new IllegalArgumentException("Captcha Servivce not initialized.")).stream()
				.filter(service -> service.supports(clazz, id)).findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Captcha Servivce with id " + id + " doesnot exists"));
	}
}

package com.nyayas.common.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;

@Component
public class ServiceFactory<T extends Service<T>> {

	@Resource
	private List<T> services;

	public T getService(Class<T> clazz, Object id) {
		return Optional.ofNullable(services).orElseThrow(() -> new IllegalStateException("No Service initialized."))
				.stream().filter(service -> service.supports(clazz, id)).findFirst()
				.orElseThrow(() -> new IllegalArgumentException("No service with id " + id + " exist."));
	}
}

package com.nyayas.common.service;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

@Component
public class CourtServiceFactory<T extends Service<T>> {

	@Resource
	private List<T> courtServices;

	public T getService(Class<T> clazz, Object id) {
		return Optional.ofNullable(courtServices)
				.orElseThrow(() -> new IllegalStateException("No Service initialized.")).stream()
				.filter(service -> service.supports(clazz, id)).findFirst()
				.orElseThrow(() -> new IllegalArgumentException("No service with id " + id + " exist."));
	}
}

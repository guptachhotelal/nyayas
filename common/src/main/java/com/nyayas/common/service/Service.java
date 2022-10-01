package com.nyayas.common.service;

@FunctionalInterface
public interface Service<T> {
	boolean supports(Class<T> clazz, Object id);
}

package com.nyayas.common.service;

import org.junit.jupiter.api.Test;

import jakarta.annotation.Resource;

class ServiceFactoryTest {

    @Resource
    private ServiceFactory<?> serviceFactory;

    @Test
    void testIllegalStateException() {
	// List<Service<?>> list= null;

	// Service<?> service=serviceFactory.getService(null, list);
    }

    @Test
    void testIllegalArgumentException() {
    }

    @Test
    void testGetService() {
    }
}

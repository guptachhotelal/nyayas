package com.nyayas.captcha.service.impl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.nyayas.captcha.CaptchaServiceApplicationTest;
import com.nyayas.captcha.service.CaptchaService;
import com.nyayas.captcha.util.Constant;

class ExternalCaptchaServiceTest extends CaptchaServiceApplicationTest {

	private CaptchaService ecs = null;

	@BeforeEach
	void before() {
		ecs = serviceFactory.getService(CaptchaService.class, Constant.EXTERNAL);
	}

	@Test
	void testSupportsText() {
		boolean supports = ecs.supports(CaptchaService.class, Constant.TEXT);
		assertFalse(supports);
	}

	@Test
	void testSupportsInternal() {
		boolean supports = ecs.supports(CaptchaService.class, Constant.INTERNAL);
		assertFalse(supports);
	}

	@Test
	void testSupportsExternal() {
		boolean supports = ecs.supports(CaptchaService.class, Constant.EXTERNAL);
		assertTrue(supports);
	}

}

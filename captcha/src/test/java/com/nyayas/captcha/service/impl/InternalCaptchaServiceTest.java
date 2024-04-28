package com.nyayas.captcha.service.impl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.nyayas.captcha.CaptchaServiceApplicationTest;
import com.nyayas.captcha.service.CaptchaService;
import com.nyayas.captcha.util.Constant;

class InternalCaptchaServiceTest extends CaptchaServiceApplicationTest {

    private CaptchaService ics = null;

    @BeforeEach
    void before() {
	ics = serviceFactory.getService(CaptchaService.class, Constant.INTERNAL);
    }

    @Test
    void testSupportsText() {
	boolean supports = ics.supports(CaptchaService.class, Constant.TEXT);
	assertFalse(supports);
    }

    @Test
    void testSupportsInternal() {
	boolean supports = ics.supports(CaptchaService.class, Constant.INTERNAL);
	assertTrue(supports);
    }

    @Test
    void testSupportsExternal() {
	boolean supports = ics.supports(CaptchaService.class, Constant.EXTERNAL);
	assertFalse(supports);
    }

}

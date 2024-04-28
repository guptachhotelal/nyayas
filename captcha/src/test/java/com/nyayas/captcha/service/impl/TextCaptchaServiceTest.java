package com.nyayas.captcha.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.nyayas.captcha.CaptchaServiceApplicationTest;
import com.nyayas.captcha.service.CaptchaService;
import com.nyayas.captcha.util.Constant;

public class TextCaptchaServiceTest extends CaptchaServiceApplicationTest {

    private CaptchaService tcs = null;

    @BeforeEach
    void before() {
	tcs = serviceFactory.getService(CaptchaService.class, Constant.TEXT);
    }

    @Test
    void testSupportsText() {
	boolean supports = tcs.supports(CaptchaService.class, Constant.TEXT);
	assertTrue(supports);
    }

    @Test
    void testSupportsInternal() {
	boolean supports = tcs.supports(CaptchaService.class, Constant.INTERNAL);
	assertFalse(supports);
    }

    @Test
    void testSupportsExternal() {
	boolean supports = tcs.supports(CaptchaService.class, Constant.EXTERNAL);
	assertFalse(supports);
    }

    @Test
    void testCaptchaEmptyRequest() {
	Map<String, String> captcha = tcs.captcha(new HashMap<>());
	assertNotNull(captcha);
    }

    @Test
    void testCaptchaNotEmptyRequest() {
	int length = 5;
	Map<String, String> map = new HashMap<>();
	map.put(Constant.LENGTH, String.valueOf(length));
	Map<String, String> captcha = tcs.captcha(map);
	assertNotNull(captcha);
	assertEquals(captcha.get(Constant.VALUE).length(), length);
    }

    @Test
    void testTextCaptacha() throws Exception {
	Map<String, String> param = new HashMap<>();
	param.put(Constant.TYPE, Constant.TEXT);
	param.put(Constant.LENGTH, String.valueOf(6));
	String requestBody = mapper.writeValueAsString(param);
	RequestBuilder builder = post(uri()).content(requestBody).accept(MediaType.APPLICATION_JSON)
		.contentType(MediaType.APPLICATION_JSON);
	mockMvc.perform(builder).andDo(MockMvcResultHandlers.print()).andExpect(status().isOk());
    }

}

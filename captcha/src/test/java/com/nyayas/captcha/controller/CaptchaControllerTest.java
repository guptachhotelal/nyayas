package com.nyayas.captcha.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.nyayas.captcha.CaptchaServiceApplicationTest;
import com.nyayas.captcha.util.Constant;

class CaptchaControllerTest extends CaptchaServiceApplicationTest {

	@Test
	void test() throws Exception {
		//assertEquals(HttpStatus.OK.value(), testCommon().getResponse().getStatus());
		testCommon();
	}

	private MvcResult testCommon() throws Exception {
		MultiValueMap<String, String> param = new LinkedMultiValueMap<>();
		param.put(Constant.TYPE, Arrays.asList(Constant.TEXT));
		RequestBuilder builder = post(host() + "v1/captcha").params(param);
		return mockMvc.perform(builder).andDo(MockMvcResultHandlers.print()).andReturn();
	}

}

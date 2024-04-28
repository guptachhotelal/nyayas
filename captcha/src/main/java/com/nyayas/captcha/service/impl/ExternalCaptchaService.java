package com.nyayas.captcha.service.impl;

import java.util.Collections;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.nyayas.captcha.service.AbstractCaptchaService;
import com.nyayas.captcha.service.CaptchaService;
import com.nyayas.captcha.util.Constant;

@Service
public class ExternalCaptchaService extends AbstractCaptchaService {

    @Override
    public boolean supports(Class<CaptchaService> clazz, Object id) {
	return CaptchaService.class.equals(clazz) && Constant.EXTERNAL.equalsIgnoreCase(String.valueOf(id));
    }

    @Override
    public Map<String, String> captcha(Map<String, String> param) {
	return Collections.emptyMap();
    }
}

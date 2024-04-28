package com.nyayas.captcha.service;

import java.util.Map;

public abstract class AbstractCaptchaService implements CaptchaService {

    @Override
    public Map<String, String> captcha(Map<String, String> param) {
	return param;
    }
}

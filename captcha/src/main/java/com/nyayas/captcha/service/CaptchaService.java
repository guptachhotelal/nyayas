package com.nyayas.captcha.service;

import java.util.Map;

import com.nyayas.common.service.Service;

public interface CaptchaService extends Service<CaptchaService> {
    Map<String, String> captcha(Map<String, String> param);
}

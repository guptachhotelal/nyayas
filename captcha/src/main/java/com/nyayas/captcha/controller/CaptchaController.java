package com.nyayas.captcha.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nyayas.captcha.service.CaptchaService;
import com.nyayas.captcha.util.Constant;
import com.nyayas.common.service.ServiceFactory;

import jakarta.annotation.Resource;

@RestController
@RequestMapping("v1")
public class CaptchaController {

    @Resource
    private ServiceFactory<CaptchaService> captchaFactory;

    @PostMapping("captcha")
    public ResponseEntity<Map<String, String>> solve(@RequestBody Map<String, String> param) {
	CaptchaService cs = captchaFactory.getService(CaptchaService.class, param.get(Constant.TYPE));
	return ResponseEntity.ok(cs.captcha(param));
    }

}

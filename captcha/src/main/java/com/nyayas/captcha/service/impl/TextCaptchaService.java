package com.nyayas.captcha.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.nyayas.captcha.service.AbstractCaptchaService;
import com.nyayas.captcha.service.CaptchaService;
import com.nyayas.captcha.util.Constant;
import com.nyayas.common.constant.CaseFields;

@Service
public class TextCaptchaService extends AbstractCaptchaService {

    @Override
    public boolean supports(Class<CaptchaService> clazz, Object id) {
	return CaptchaService.class.equals(clazz) && Constant.TEXT.equalsIgnoreCase(String.valueOf(id));
    }

    @Override
    public Map<String, String> captcha(Map<String, String> param) {
	int length = 3;
	if (param.containsKey(Constant.LENGTH) && !"".equals(param.get(Constant.LENGTH))) {
	    length = Integer.parseInt(param.get(Constant.LENGTH));
	}

	int max = (int) Math.pow(10, length - 1);
	int captcha = (int) (Math.random() * max * 10);
	if (captcha < max) {
	    captcha += max;
	}

	Map<String, String> map = super.captcha(param);
	map.put(Constant.VALUE, String.valueOf(captcha));
	return map;
    }

    public static void main(String[] args) {
	CaptchaService cs = new TextCaptchaService();
	Map<String, String> map = new HashMap<>();
	map.put(CaseFields.COURT_ID, "123");
	map.put(Constant.LENGTH, "4");
	System.out.println(cs.captcha(map));
    }
}

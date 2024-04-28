package com.nyayas.captcha.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.nyayas.captcha.service.AbstractCaptchaService;
import com.nyayas.captcha.service.CaptchaService;
import com.nyayas.captcha.util.Constant;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

@Service
public class InternalCaptchaService extends AbstractCaptchaService {

    private static final String TESSDATA_PATH = "./tessdata";

    @Override
    public boolean supports(Class<CaptchaService> clazz, Object id) {
	return CaptchaService.class.equals(clazz) && Constant.INTERNAL.equalsIgnoreCase(String.valueOf(id));
    }

    @Override
    public Map<String, String> captcha(Map<String, String> param) {
	Map<String, String> map = super.captcha(param);
	map.put(Constant.VALUE, text(param.get(Constant.FILE_PATH)));
	return map;
    }

    private String text(String filePath) {
	ITesseract tesseract = new Tesseract();
	tesseract.setDatapath(TESSDATA_PATH);
	try {
	    return tesseract.doOCR(new File(filePath)).trim();
	} catch (TesseractException te) {
	    throw new RuntimeException(te.getMessage());
	}
    }

    public static void main(String[] args) {
	Map<String, String> map = new HashMap<>();
	map.put(Constant.FILE_PATH, "d:/data/cap1.PNG");
	System.out.println(new InternalCaptchaService().captcha(map));
    }
}

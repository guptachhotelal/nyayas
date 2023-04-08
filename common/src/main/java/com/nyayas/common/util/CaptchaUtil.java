package com.nyayas.common.util;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;
import java.util.Map;
import java.util.Scanner;

import org.jsoup.Connection.Response;

public class CaptchaUtil {

	public static final String TEMP_DIR = System.getProperty("java.io.tmpdir");

	public static final String captcha(String url, Map<String, String> cookies) throws IOException {
		Response resp = JSoupHelper.getConnection(url, 0).cookies(cookies).execute();
		return captcha(resp);
	}

	public static final String captcha(Response response) throws IOException {
		String filePath = TEMP_DIR + File.separator + System.currentTimeMillis() + ".png";
		OutputStream os = new FileOutputStream(filePath);
		os.write(response.bodyAsBytes());
		os.close();
		Desktop.getDesktop().open(new File(filePath));
		Scanner sc = new Scanner(System.in);
		String input = sc.next();
		sc.close();
		return input;
	}

	public static void main(String[] args) throws Exception {
		String url = "https://hcservices.ecourts.gov.in/ecourtindiaHC/securimage/securimage_show.php";
		System.out.println(captcha(url, Collections.emptyMap()));
	}
}

package com.nyayas.status.service.court;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.nyayas.common.constant.CaseFields;
import com.nyayas.common.constant.Courts;
import com.nyayas.common.util.JSoupHelper;
import com.nyayas.common.util.ResponseUtil;
import com.nyayas.status.service.CaseNumberService;
import com.nyayas.status.service.CaseStatusByCaseNumberService;

@Service
public class DelhiHighCourt extends CaseStatusByCaseNumberService {

	public static final String STATUS_HOME = "https://delhihighcourt.nic.in/case.asp";

	public static final String STATUS_URL = "https://delhihighcourt.nic.in/court/dhc_case_status_list_new?";

	private static final String[] FIELDS = { "ctype", "cno", "cyear", "sno" };

	@Override
	public boolean supports(Class<CaseNumberService> clazz, Object id) {
		return CaseNumberService.class.equals(clazz) && id.equals(Courts.DELHI_HIGH_COURT.code());
	}

	@Override
	public Map<String, String> caseNumberParam(Map<String, String> param) {
		Map<String, String> map = super.caseNumberParam(param);
		map.put("ctype", param.get(CaseFields.CASE_TYPE));
		map.put("cno", param.get(CaseFields.CASE_NUMBER));
		map.put("cyear", param.get(CaseFields.CASE_YEAR));
		map.put("sno", "1");
		return map;
	}

	@Override
	public Map<String, Object> byCaseNumber(Map<String, String> param) throws IOException {
		int captcha = (int) (Math.random() * 10000);
		if (captcha < 1000) {
			captcha += 1000;
		}

		String sCaptcha = String.valueOf(captcha);
		param.put("sno", "1");
		param.put("inputdigit", sCaptcha);
		param.put("hiddeninputdigit", sCaptcha);

		StringBuilder sbURL = new StringBuilder(STATUS_URL);
		Arrays.asList(FIELDS).stream().forEach(field -> {
			sbURL.append(field).append("=").append(param.get(field)).append("&");
		});
		String statusURL = String.valueOf(sbURL);
		sbURL.setLength(0);

		Document sDoc = JSoupHelper.getResponse(statusURL, 0).parse();
		Elements trs = sDoc.getElementById("InnerPageContent").getElementsByTag("td");

		Map<String, Object> map = new LinkedHashMap<>();

		for (int i = 0; i < trs.size(); i++) {
			String text = trs.get(i).text();
			switch (i) {
			case 0:
				break;
			case 1:
				if (text.contains(" [") && text.contains("] ")) {
					map.put(CaseFields.CASE_STAGE, text.substring(text.indexOf('[') + 1, text.indexOf(']')));
				}
				break;
			case 2:
				if (text.contains("Advocate")) {
					text = text.substring(0, text.indexOf("Advocate"));
				}
				map.put(CaseFields.CASE_TITLE, text);
				break;
			case 3:
				break;
			}
		}
		return ResponseUtil.data(param, map);
	}
}

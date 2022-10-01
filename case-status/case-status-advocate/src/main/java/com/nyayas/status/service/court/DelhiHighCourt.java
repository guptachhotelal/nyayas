package com.nyayas.status.service.court;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nyayas.common.constant.CaseFields;
import com.nyayas.common.constant.Courts;
import com.nyayas.common.util.JSoupHelper;
import com.nyayas.common.util.ResponseUtil;
import com.nyayas.status.service.AdvocateService;
import com.nyayas.status.service.CaseStatusByAdvocateService;

@Service
public class DelhiHighCourt extends CaseStatusByAdvocateService {

	public static final String STATUS_HOME = "https://delhihighcourt.nic.in/case.asp";

	public static final String STATUS_URL = "https://delhihighcourt.nic.in/dhc_case_status_list_new.asp";

	@Override
	public boolean supports(Class<AdvocateService> clazz, Object id) {
		return AdvocateService.class.equals(clazz) && id.equals(Courts.DELHI_HIGH_COURT.courtId());
	}

	@Override
	public Map<String, String> advocateNameParam(Map<String, String> param) {
		Map<String, String> map = super.advocateNameParam(param);
		map.put("ctype_29", param.get(CaseFields.CASE_TYPE));
		map.put("cno", param.get(CaseFields.CASE_NUMBER));
		map.put("cyear", param.get(CaseFields.CASE_YEAR));
		return map;
	}

	@Override
	public Map<String, Object> byAdvocateName(Map<String, String> param) throws IOException {
		int captcha = (int) (Math.random() * 10000);
		if (captcha < 1000) {
			captcha += 1000;
		}

		String sCaptcha = String.valueOf(captcha);
		param.put("sno", "1");
		param.put("inputdigit", sCaptcha);
		param.put("hiddeninputdigit", sCaptcha);

		Document sDoc = JSoupHelper.postConnection(STATUS_URL, 0).data(param).execute().parse();
		Elements spans = sDoc.select("div#InnerPageContent ul li span");
		Map<String, Object> map = new LinkedHashMap<>();

		for (int i = 1; i < spans.size(); i++) {
			String text = spans.get(i).text();
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

	public static void main(String[] args) throws IOException {
		Map<String, String> map = new HashMap<>();
		map.put(CaseFields.CASE_NUMBER, "1");
		map.put(CaseFields.CASE_TYPE, "ARB. A. (COMM.)");
		map.put(CaseFields.CASE_YEAR, "2022");

		AdvocateService css = new DelhiHighCourt();

		Map<String, Object> resp = css.byAdvocateName(css.advocateNameParam(map));

		System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(resp));
	}
}


package com.nyayas.status.service.court;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nyayas.status.service.CaseStatusByAdvocateService;
import com.nyayas.common.constant.CaseFields;
import com.nyayas.common.constant.Courts;
import com.nyayas.common.util.JSoupHelper;
import com.nyayas.common.util.ResponseUtil;
import com.nyayas.status.service.AdvocateService;

@Service
public class SupremeCourt extends CaseStatusByAdvocateService {

	public static final String STATUS_HOME = "https://main.sci.gov.in/case-status";

	public static final String STATUS_URL = "https://main.sci.gov.in/php/case_status/case_status_process.php";

	@Override
	public boolean supports(Class<AdvocateService> clazz, Object id) {
		return AdvocateService.class.equals(clazz) && id.equals(Courts.SUPREME_COURT.code());
	}

	@Override
	public Map<String, String> advocateNameParam(Map<String, String> param) {
		Map<String, String> map = super.advocateNameParam(param);
		map.put("ct", param.get(CaseFields.CASE_TYPE));
		map.put("cn", param.get(CaseFields.CASE_NUMBER));
		map.put("cy", param.get(CaseFields.CASE_YEAR));
		return map;
	}

	@Override
	public Map<String, Object> byAdvocateName(Map<String, String> param) throws IOException {
		int captcha = (int) (Math.random() * 10000);
		if (captcha < 1000) {
			captcha += 1000;
		}
		param.put("ansCaptcha", String.valueOf(captcha));

		Response sResp = JSoupHelper.postConnection(STATUS_URL, 0).referrer(STATUS_HOME).data(param).execute();

		Document doc = Jsoup.parse(sResp.body());

		Map<String, Object> map = new LinkedHashMap<>();

		Elements h5s = doc.getElementsByTag("h5");
		Elements trs = doc.getElementsByTag("tr");

		h5s.stream().forEach(h5 -> {
			if (h5.text().toLowerCase().startsWith("diary no")) {
				map.put(CaseFields.DIARY_NUMBER, h5.text().replaceAll("(?i)Diary No.-", "").trim());
			} else {
				map.put(CaseFields.CASE_TITLE, h5.text().trim());
			}
		});

		trs.stream().forEach(tr -> {
			if (tr.text().toLowerCase().startsWith("status/stage")) {
				String status = "";
				String statusText = "";
				if (tr.childrenSize() > 1) {
					statusText = tr.child(1).text();
					status = statusText.substring(0, statusText.indexOf(' '));
				}
				map.put(CaseFields.CASE_STAGE, status);
				map.put(CaseFields.CASE_STATUS_DETAIL, statusText);
			}
		});
		return ResponseUtil.data(param, map);
	}

	public static void main(String[] args) throws Exception {
		Map<String, String> map = new HashMap<>();
		map.put(CaseFields.CASE_NUMBER, "1");
		map.put(CaseFields.CASE_TYPE, "3");
		map.put(CaseFields.CASE_YEAR, "2022");

		SupremeCourt sc = new SupremeCourt();
		Map<String, String> param = sc.advocateNameParam(map);
		ObjectMapper mapper = new ObjectMapper();
//		System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(param));

		System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(sc.byAdvocateName(param)));
	}

}

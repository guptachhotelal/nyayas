
package com.nyayas.status.service.court;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
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
public class SupremeCourt extends CaseStatusByCaseNumberService {

	public static final String STATUS_HOME = "https://main.sci.gov.in/case-status";

	public static final String CAPTCHA_URL = "https://main.sci.gov.in/php/captcha_num.php";

	public static final String STATUS_URL = "https://main.sci.gov.in/php/case_status/case_status_process.php";

	private static final String HOST_KEY = "Host";

	private static final String HOST_VALUE = "main.sci.gov.in";

	@Override
	public boolean supports(Class<CaseNumberService> clazz, Object id) {
		return CaseNumberService.class.equals(clazz) && id.equals(Courts.SUPREME_COURT.code());
	}

	@Override
	public Map<String, String> caseNumberParam(Map<String, String> param) {
		Map<String, String> map = super.caseNumberParam(param);
		map.put("ct", param.get(CaseFields.CASE_TYPE));
		map.put("cn", param.get(CaseFields.CASE_NUMBER));
		map.put("cy", param.get(CaseFields.CASE_YEAR));
		return map;
	}

	@Override
	public Map<String, Object> byCaseNumber(Map<String, String> param) throws IOException {
		Response respHome = JSoupHelper.getResponse(STATUS_HOME, HOST_KEY, HOST_VALUE, 0);

		Map<String, String> cookies = respHome.cookies();

		Response respCaptcha = JSoupHelper.getConnection(CAPTCHA_URL, HOST_KEY, HOST_VALUE, 0).cookies(cookies)
				.execute();

		param.put("ansCaptcha", respCaptcha.body());

		Response sResp = JSoupHelper.postConnection(STATUS_URL, HOST_KEY, HOST_VALUE, 0).referrer(STATUS_HOME)
				.cookies(cookies).data(param).execute();

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
}

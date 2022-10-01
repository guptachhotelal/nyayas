package com.nyayas.status.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;

import com.nyayas.common.constant.CaseFields;
import com.nyayas.common.util.ECourtHelper;
import com.nyayas.common.util.JSoupHelper;

public final class ECourtHighCourtCaseStatusUtil {

	public static final String STATUS_HOME = "https://hcservices.ecourts.gov.in/ecourtindiaHC/cases/case_no.php?state_cd=##sc##&dist_cd=##dc##&court_code=##cc##";

	public static final String CAPTCHA_URL = "https://hcservices.ecourts.gov.in/ecourtindiaHC/securimage/securimage_show.php";

	public static final String STATUS_DETAIL_URL = "https://hcservices.ecourts.gov.in/ecourtindiaHC/cases/o_civil_case_history.php";

	public static final Map<String, String> param(Map<String, String> param) {
		param.put("state_code", param.get(CaseFields.STATE_CODE));
		param.put("dist_code", param.get(CaseFields.DISTRICT_CODE));
		param.put("court_code", param.get(CaseFields.COURT_CODE));
		return param;
	}

	public static final Response home(Map<String, String> param) throws IOException {
		String sHome = STATUS_HOME.replaceAll("##sc##", param.get(CaseFields.STATE_CODE))
				.replaceAll("##dc##", param.get(CaseFields.DISTRICT_CODE))
				.replaceAll("##cc##", param.get(CaseFields.COURT_CODE));
		return JSoupHelper.getResponse(sHome, 0);
	}

	public static final String csrf(Response response) throws IOException {
//		Elements scripts = response.parse().getElementsByTag("script");
//		String csrfToken = "";
//		for (Element script : scripts) {
//			if (script.html().contains("csrfMagicToken")) {
//				String csrf = script.data();
//				csrfToken = csrf.substring(csrf.indexOf('"') + 1, csrf.indexOf(";var") - 1);
//				break;
//			}
//		}
//		return csrfToken;
		String html = response.parse().html();
		return html.substring(html.indexOf("sid:"), html.indexOf(";var") - 1);
	}

	public static final Response status(String url, Map<String, String> cookies, Map<String, String> param)
			throws IOException {
		param.put("action_code", "showRecords");
		param.put("appFlag", "");
		return JSoupHelper.postConnection(url, 0).cookies(cookies).data(param).execute();
	}

	public static final List<Map<String, Object>> statusDetail(Response resp, String url, Map<String, String> param)
			throws IOException {
		String[] cases = resp.body().replaceAll("[^\\x00-\\x7F]", "").trim().split("##");
		List<Map<String, Object>> caseList = new ArrayList<>();
		for (String part : cases) {
			String[] parts = part.split("~");
			param.put("case_no", parts[0].trim());
			param.put("cino", parts[3].trim());
			Document sdDoc = JSoupHelper.postConnection(url, 0).data(param).execute().parse();
			Map<String, Object> map = new LinkedHashMap<>();
			map.put(CaseFields.CASE_NUMBER, parts[0].trim());
			String caseTile = parts[2].replaceAll("<br />", " ").replace('\n', ' ').replaceAll("\\s+", " ").trim();
			map.put(CaseFields.CASE_TITLE, caseTile);
			map.put(CaseFields.CASE_DETAIL, ECourtHelper.caseDetail(sdDoc));
			map.put(CaseFields.CASE_STATUS, ECourtHelper.caseStatus(sdDoc));
			caseList.add(map);
			break;
		}
		return caseList;
	}
}

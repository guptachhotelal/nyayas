package com.nyayas.status.service.court;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Connection.Response;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nyayas.common.constant.CaseFields;
import com.nyayas.common.constant.Courts;
import com.nyayas.common.util.CaptchaUtil;
import com.nyayas.common.util.ResponseUtil;
import com.nyayas.status.service.ActService;
import com.nyayas.status.service.CaseStatusByActService;
import com.nyayas.status.util.ECourtHighCourtCaseStatusUtil;

@Service
public class ECourtHighCourt extends CaseStatusByActService {

	public static final String STATUS_HOME = "https://hcservices.ecourts.gov.in/ecourtindiaHC/cases/case_no.php?state_cd=##sc##&dist_cd=##dc##&court_code=##cc##";

	public static final String CAPTCHA_URL = "https://hcservices.ecourts.gov.in/ecourtindiaHC/securimage/securimage_show.php";

	public static final String STATUS_URL = "https://hcservices.ecourts.gov.in/ecourtindiaHC/cases/case_no_qry.php";

	public static final String STATUS_DETAIL_URL = "https://hcservices.ecourts.gov.in/ecourtindiaHC/cases/o_civil_case_history.php";

	@Override
	public boolean supports(Class<ActService> clazz, Object id) {
		return ActService.class.equals(clazz) && id.equals(Courts.ECOURT_HIGH_COURT.code());
	}

	@Override
	public Map<String, String> param(Map<String, String> param) {
		Map<String, String> map = super.param(param);
		map.putAll(ECourtHighCourtCaseStatusUtil.param(param));

		map.put("case_type", param.get(CaseFields.CASE_TYPE));
		map.put("case_no", param.get(CaseFields.CASE_NUMBER));
		map.put("rgyear", param.get(CaseFields.CASE_YEAR));

		map.put("caseNoType", "new");
		map.put("displayOldCaseNo", "NO");
		return map;
	}

	@Override
	public Map<String, Object> caseStatus(Map<String, String> param) throws IOException {
		Response hResp = ECourtHighCourtCaseStatusUtil.home(param);
		Map<String, String> cookies = hResp.cookies();
		param.put("__csrf_magic", ECourtHighCourtCaseStatusUtil.csrf(hResp));

		param.put("captcha", CaptchaUtil.captcha(CAPTCHA_URL, cookies));

		Response sResp = ECourtHighCourtCaseStatusUtil.status(STATUS_URL, cookies, param);

		List<Map<String, Object>> caseList = ECourtHighCourtCaseStatusUtil.statusDetail(sResp, STATUS_DETAIL_URL,
				param);
		return ResponseUtil.data(param, caseList);
	}

	public static void main(String[] args) throws Exception {
		ActService sc = new ECourtHighCourt();
		Map<String, String> map = new HashMap<>();
		map.put(CaseFields.STATE_CODE, "1");
		map.put(CaseFields.DISTRICT_CODE, "1");
		map.put(CaseFields.COURT_CODE, "1");

		map.put(CaseFields.CASE_NUMBER, "1");
		map.put(CaseFields.CASE_TYPE, "332");
		map.put(CaseFields.CASE_YEAR, "2022");

		Map<String, String> param = sc.param(map);
		ObjectMapper mapper = new ObjectMapper();

//		mapper.writeValueAsBytes(sc.byCaseNumber(param));
		System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(sc.caseStatus(param)));
	}

}
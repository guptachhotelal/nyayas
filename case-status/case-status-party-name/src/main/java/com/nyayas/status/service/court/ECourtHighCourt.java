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
import com.nyayas.status.service.CaseStatusByPartyNameService;
import com.nyayas.status.service.PartyNameService;
import com.nyayas.status.util.ECourtHighCourtCaseStatusUtil;

@Service
public class ECourtHighCourt extends CaseStatusByPartyNameService {

	public static final String STATUS_HOME = "https://hcservices.ecourts.gov.in/ecourtindiaHC/cases/case_no.php?state_cd=##sc##&dist_cd=##dc##&court_code=##cc##";

	public static final String CAPTCHA_URL = "https://hcservices.ecourts.gov.in/ecourtindiaHC/securimage/securimage_show.php";

	public static final String STATUS_URL = "https://hcservices.ecourts.gov.in/ecourtindiaHC/cases/ki_petres_qry.php";

	public static final String STATUS_DETAIL_URL = "https://hcservices.ecourts.gov.in/ecourtindiaHC/cases/o_civil_case_history.php";

	@Override
	public boolean supports(Class<PartyNameService> clazz, Object id) {
		return PartyNameService.class.equals(clazz) && id.equals(Courts.ECOURT_HIGH_COURT.code());
	}

	@Override
	public Map<String, String> param(Map<String, String> param) {
		Map<String, String> map = super.param(param);
		map.putAll(ECourtHighCourtCaseStatusUtil.param(param));
		map.put("petres_name", param.get(CaseFields.CASE_PARTY_NAME));
		map.put("f", param.get(CaseFields.CASE_STAGE));
		map.put("rgyear", param.get(CaseFields.CASE_YEAR));

		return map;
	}

	@Override
	public Map<String, Object> caseStatus(Map<String, String> param) throws IOException {
		Response hResp = ECourtHighCourtCaseStatusUtil.home(param);
		Map<String, String> cookies = hResp.cookies();
		param.put("__csrf_magic", ECourtHighCourtCaseStatusUtil.csrf(hResp));

		param.put("captcha", CaptchaUtil.captcha(ECourtHighCourtCaseStatusUtil.CAPTCHA_URL, cookies));

		Response sResp = ECourtHighCourtCaseStatusUtil.status(STATUS_URL, cookies, param);

		List<Map<String, Object>> caseList = ECourtHighCourtCaseStatusUtil.statusDetail(sResp,
				ECourtHighCourtCaseStatusUtil.STATUS_DETAIL_URL, param);
		return ResponseUtil.data(param, caseList);
	}

	public static void main(String[] args) throws Exception {
		PartyNameService sc = new ECourtHighCourt();
		Map<String, String> map = new HashMap<>();
		map.put(CaseFields.STATE_CODE, "1");
		map.put(CaseFields.DISTRICT_CODE, "1");
		map.put(CaseFields.COURT_CODE, "1");

		map.put(CaseFields.CASE_PARTY_NAME, "ANKUSH KISAN PADWAL");
		map.put(CaseFields.CASE_STAGE, "Pending");
		map.put(CaseFields.CASE_YEAR, "2022");

		Map<String, String> param = sc.param(map);
		ObjectMapper mapper = new ObjectMapper();

//		mapper.writeValueAsBytes(sc.byCaseNumber(param));
		System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(sc.caseStatus(param)));
	}

}
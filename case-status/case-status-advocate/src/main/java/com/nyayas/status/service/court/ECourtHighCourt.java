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
import com.nyayas.status.service.AdvocateService;
import com.nyayas.status.service.CaseStatusByAdvocateService;
import com.nyayas.status.util.ECourtHighCourtCaseStatusUtil;

@Service
public class ECourtHighCourt extends CaseStatusByAdvocateService {

	public static final String STATUS_URL = "https://hcservices.ecourts.gov.in/ecourtindiaHC/cases/qs_civil_advocate_qry.php";

	@Override
	public boolean supports(Class<AdvocateService> clazz, Object id) {
		return AdvocateService.class.equals(clazz) && id.equals(Courts.ECOURT_HIGH_COURT.code());
	}

	@Override
	public Map<String, String> advocateNameParam(Map<String, String> param) {
		Map<String, String> map = super.advocateNameParam(param);
		map.putAll(ECourtHighCourtCaseStatusUtil.param(param));
		map.put("advocate_name", param.get(CaseFields.CASE_ADVOCATE_NAME));
		map.put("f", param.get(CaseFields.CASE_STAGE));
		return map;
	}

	@Override
	public Map<String, Object> byAdvocateName(Map<String, String> param) throws IOException {
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
		AdvocateService sc = new ECourtHighCourt();
		Map<String, String> map = new HashMap<>();
		map.put(CaseFields.STATE_CODE, "1");
		map.put(CaseFields.DISTRICT_CODE, "1");
		map.put(CaseFields.COURT_CODE, "1");
		map.put(CaseFields.CASE_STAGE, "Pending");

		map.put(CaseFields.CASE_ADVOCATE_NAME, "gaurav sharma");

		Map<String, String> param = sc.advocateNameParam(map);
		ObjectMapper mapper = new ObjectMapper();

//		mapper.writeValueAsBytes(sc.byCaseNumber(param));
		System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(sc.byAdvocateName(param)));
	}

}
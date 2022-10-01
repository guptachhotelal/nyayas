package com.nyayas.status.service.court;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nyayas.common.constant.CaseFields;
import com.nyayas.common.constant.Courts;
import com.nyayas.common.util.CaptchaUtil;
import com.nyayas.common.util.ECourtHelper;
import com.nyayas.common.util.JSoupHelper;
import com.nyayas.common.util.ResponseUtil;
import com.nyayas.status.service.CaseStatusByPartyNameService;
import com.nyayas.status.service.PartyNameService;

@Service
public class ECourtDistrictCourt extends CaseStatusByPartyNameService {

	public static final String STATUS_HOME = "https://services.ecourts.gov.in/ecourtindia_v4_bilingual/cases/case_no.php?state=D&state_cd=##sc##&dist_cd=##dc##";

	public static final String CAPTCHA_URL = "https://services.ecourts.gov.in/ecourtindia_v4_bilingual/securimage/securimage_show.php";

	public static final String STATUS_URL = "https://services.ecourts.gov.in/ecourtindia_v4_bilingual/cases/case_no_qry.php";

	public static final String STATUS_DETAIL_URL = "https://services.ecourts.gov.in/ecourtindia_v4_bilingual/cases/o_civil_case_history.php";

	@Override
	public boolean supports(Class<PartyNameService> clazz, Object id) {
		return PartyNameService.class.equals(clazz) && id.equals(Courts.ECOURT_DISTRICT_COURT.courtId());
	}

	@Override
	public Map<String, String> param(Map<String, String> param) {
		Map<String, String> map = super.param(param);

		map.put("case_type", param.get(CaseFields.CASE_TYPE));
		map.put("case_no", param.get(CaseFields.CASE_NUMBER));
		map.put("rgyear", param.get(CaseFields.CASE_YEAR));

		map.put("state_code", param.get(CaseFields.STATE_CODE));
		map.put("dist_code", param.get(CaseFields.DISTRICT_CODE));
		map.put("court_codeArr", param.get(CaseFields.COURT_CODE));

		map.put("caseNoType", "new");
		map.put("displayOldCaseNo", "NO");
		return map;
	}

	@Override
	public Map<String, Object> caseStatus(Map<String, String> param) throws IOException {
//		String sHome = STATUS_HOME.replaceAll("##sc##", param.get(CaseFields.STATE_CODE)).replaceAll("##dc##",
//				param.get(CaseFields.DISTRICT_CODE));
		Response cResp = JSoupHelper.getResponse(CAPTCHA_URL, 0);
		String captcha = CaptchaUtil.captcha(cResp);
		param.put("action_code", "showRecords");
		param.put("captcha", captcha);
		Response sResp = JSoupHelper.postConnection(STATUS_URL, 0).cookies(cResp.cookies()).data(param).execute();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonCon = mapper.readTree(sResp.body()).get("con");
		Map<String, Object> map = new HashMap<>();
		Map<String, String> temp = new HashMap<>();
		if (jsonCon.isArray()) {
			JsonNode caseDetail = mapper.readTree(jsonCon.get(0).asText());
			if (caseDetail.isArray()) {
				JsonNode caseObj = caseDetail.get(0);
				String caseTitle = caseObj.get("pet_name").asText() + " Versus " + caseObj.get("res_name").asText();
				map.put(CaseFields.CASE_TITLE, caseTitle);
//				map.put(CaseFields.CIN_NUMBER, caseObj.get("cino"));
				param.put("case_no", caseObj.get("case_no").asText());
				param.put("cino", caseObj.get("cino").asText());
				temp.put("case_no", caseObj.get("case_no").asText());
				temp.put("cino", caseObj.get("cino").asText());
				temp.put("court_code", caseObj.get("case_no2").asText());
			}
		}

		temp.put("appFlag", "");
		temp.put("dist_code", param.get(CaseFields.DISTRICT_CODE));
		temp.put("state_code", param.get(CaseFields.STATE_CODE));
		Document sdDoc = JSoupHelper.postConnection(STATUS_DETAIL_URL, 0).data(temp).execute().parse();
		map.putAll(ECourtHelper.caseDetail(sdDoc));
		map.putAll(ECourtHelper.caseStatus(sdDoc));
		return ResponseUtil.data(param, map);
	}

	public static void main(String[] args) throws Exception {
		PartyNameService sc = new ECourtDistrictCourt();
		Map<String, String> map = new HashMap<>();
		map.put(CaseFields.STATE_CODE, "26");
		map.put(CaseFields.DISTRICT_CODE, "4");
		map.put(CaseFields.COURT_CODE, "1,2,3,4");

		map.put(CaseFields.CASE_NUMBER, "1");
		map.put(CaseFields.CASE_TYPE, "20");
		map.put(CaseFields.CASE_YEAR, "2022");

		Map<String, String> param = sc.param(map);
		ObjectMapper mapper = new ObjectMapper();

		System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(sc.caseStatus(param)));
//		Document doc=Jsoup.parse(new File("C:/Users/Chhotelal R Gupta/Desktop/sci.htm"));
//		Elements strongs= doc.select("label>strong");
//		System.out.println(strongs);
	}
}

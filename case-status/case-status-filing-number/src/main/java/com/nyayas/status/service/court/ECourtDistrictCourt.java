package com.nyayas.status.service.court;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nyayas.common.constant.CaseFields;
import com.nyayas.common.constant.Courts;
import com.nyayas.status.service.CaseStatusByFilingNumberService;
import com.nyayas.status.service.FilingNumberService;

@Service
public class ECourtDistrictCourt extends CaseStatusByFilingNumberService {

	public static final String STATUS_HOME = "https://services.ecourts.gov.in/ecourtindia_v4_bilingual/cases/case_no.php?state=D&state_cd=##sc##&dist_cd=##dc##";

	public static final String CAPTCHA_URL = "https://services.ecourts.gov.in/ecourtindia_v4_bilingual/securimage/securimage_show.php";

	public static final String STATUS_URL = "https://services.ecourts.gov.in/ecourtindia_v4_bilingual/cases/case_no_qry.php";

	public static final String STATUS_DETAIL_URL = "https://services.ecourts.gov.in/ecourtindia_v4_bilingual/cases/o_civil_case_history.php";

	@Override
	public boolean supports(Class<FilingNumberService> clazz, Object id) {
		return FilingNumberService.class.equals(clazz) && id.equals(Courts.ECOURT_DISTRICT_COURT.code());
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
		return Collections.emptyMap();
	}

	public static void main(String[] args) throws Exception {
		FilingNumberService sc = new ECourtDistrictCourt();
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

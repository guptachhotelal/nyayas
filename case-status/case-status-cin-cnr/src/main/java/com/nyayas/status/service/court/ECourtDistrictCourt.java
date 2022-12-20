package com.nyayas.status.service.court;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nyayas.common.constant.CaseFields;
import com.nyayas.common.constant.Courts;
import com.nyayas.status.service.CINCNRService;
import com.nyayas.status.service.CaseStatusByCINCNRService;
import com.nyayas.status.util.ECourtHighCourtCaseStatusUtil;

@Service
public class ECourtDistrictCourt extends CaseStatusByCINCNRService {

	public static final String STATUS_URL = "https://services.ecourts.gov.in/ecourtindia_v4_bilingual/cases/case_no_qry.php";

	@Override
	public boolean supports(Class<CINCNRService> clazz, Object id) {
		return CINCNRService.class.equals(clazz) && id.equals(Courts.ECOURT_DISTRICT_COURT.code());
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
	public Map<String, Object> byCINCNR(Map<String, String> param) throws IOException {
		return super.byCINCNR(param);
	}

	public static void main(String[] args) throws Exception {
		CINCNRService sc = new ECourtDistrictCourt();
		Map<String, String> map = new HashMap<>();
		map.put(CaseFields.STATE_CODE, "26");
		map.put(CaseFields.DISTRICT_CODE, "4");
		map.put(CaseFields.COURT_CODE, "1,2,3,4");

		map.put(CaseFields.CASE_NUMBER, "1");
		map.put(CaseFields.CASE_TYPE, "20");
		map.put(CaseFields.CASE_YEAR, "2022");

		Map<String, String> param = sc.param(map);
		ObjectMapper mapper = new ObjectMapper();

		System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(sc.byCINCNR(param)));
//		Document doc=Jsoup.parse(new File("C:/Users/Chhotelal R Gupta/Desktop/sci.htm"));
//		Elements strongs= doc.select("label>strong");
//		System.out.println(strongs);
	}
}

package com.nyayas.status.service.court;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import com.nyayas.common.constant.CaseFields;
import com.nyayas.status.service.CaseStatusByCaseNumberService;

public abstract class BombayHighCourt extends CaseStatusByCaseNumberService {

	public static final String STATUS_HOME = "https://bombayhighcourt.nic.in/case_query.php";

	public static final String STATUS_URL = "https://bombayhighcourt.nic.in/casequery_action.php";

	@Override
	public Map<String, String> caseNumberParam(Map<String, String> param) {
		Map<String, String> map = super.caseNumberParam(param);
		map.put("m_skey", param.get(CaseFields.CASE_TYPE));
		map.put("m_no", param.get(CaseFields.CASE_NUMBER));
		map.put("m_yr", param.get(CaseFields.CASE_YEAR));
		return map;
	}

	@Override
	public Map<String, Object> byCaseNumber(Map<String, String> param) throws IOException {

		return Collections.emptyMap();
	}

	public static void main(String[] args) {

	}
}

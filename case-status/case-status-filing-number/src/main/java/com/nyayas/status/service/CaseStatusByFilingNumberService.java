package com.nyayas.status.service;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

public abstract class CaseStatusByFilingNumberService implements FilingNumberService {

	@Override
	public Map<String, String> param(Map<String, String> param) {
		return param;
	}

	@Override
	public Map<String, Object> caseStatus(Map<String, String> param) throws IOException {
		return Collections.emptyMap();
	}

}

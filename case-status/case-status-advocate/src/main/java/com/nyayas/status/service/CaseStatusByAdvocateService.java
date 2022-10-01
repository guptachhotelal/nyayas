package com.nyayas.status.service;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

public abstract class CaseStatusByAdvocateService extends AbstractCaseStatusService implements AdvocateService {

	@Override
	public Map<String, String> advocateNameParam(Map<String, String> param) {
		return param;
	}

	@Override
	public Map<String, String> barCodeParam(Map<String, String> param) {
		return param;
	}

	@Override
	public Map<String, String> caseListDateParam(Map<String, String> param) {
		return param;
	}

	@Override
	public Map<String, Object> byAdvocateName(Map<String, String> param) throws IOException {
		return Collections.emptyMap();
	}

	@Override
	public Map<String, Object> byBarCode(Map<String, String> param) throws IOException {
		return Collections.emptyMap();
	}

	@Override
	public Map<String, Object> byCaseListDate(Map<String, String> param) throws IOException {
		return Collections.emptyMap();
	}

}

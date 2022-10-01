package com.nyayas.status.service;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

public abstract class CaseStatusByCINCNRService extends AbstractCaseStatusService implements CINCNRService {

	@Override
	public Map<String, String> param(Map<String, String> param) {
		return param;
	}

	@Override
	public Map<String, Object> byCINCNR(Map<String, String> param) throws IOException {
		return Collections.emptyMap();
	}

}

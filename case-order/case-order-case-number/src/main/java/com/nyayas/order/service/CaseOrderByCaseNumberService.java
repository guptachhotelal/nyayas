package com.nyayas.order.service;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

public abstract class CaseOrderByCaseNumberService extends AbstractCaseOrderService implements CaseNumberService {

	@Override
	public Map<String, String> caseNumberParam(Map<String, String> param) {
		return param;
	}

	@Override
	public Map<String, Object> byCaseNumber(Map<String, String> param) throws IOException {
		return Collections.emptyMap();
	}

}

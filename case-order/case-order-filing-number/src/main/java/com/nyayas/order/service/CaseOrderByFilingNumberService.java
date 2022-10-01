package com.nyayas.order.service;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

public abstract class CaseOrderByFilingNumberService extends AbstractCaseOrderService implements FilingNumberService {

	 @Override
	public Map<String, String> filingNumberParam(Map<String, String> param) {
		return param;
	}
	 
	 @Override
	public Map<String, Object> byFilingNumber(Map<String, String> param) throws IOException {
		return Collections.emptyMap();
	}

}

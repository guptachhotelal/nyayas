package com.nyayas.order.service;

import java.io.IOException;
import java.util.Map;

import com.nyayas.common.service.Service;

public interface FilingNumberService extends Service<FilingNumberService> {

	Map<String, String> filingNumberParam(Map<String, String> param);

	Map<String, Object> byFilingNumber(Map<String, String> param) throws IOException;
}

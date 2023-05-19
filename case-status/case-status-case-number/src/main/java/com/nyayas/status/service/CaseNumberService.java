package com.nyayas.status.service;

import java.io.IOException;
import java.util.Map;

import com.nyayas.common.service.Service;

public interface CaseNumberService extends Service<CaseNumberService> {

	Map<String, String> caseNumberParam(Map<String, String> request);

	Map<String, Object> byCaseNumber(Map<String, String> request) throws IOException;
}

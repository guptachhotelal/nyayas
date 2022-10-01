package com.nyayas.status.service;

import java.io.IOException;
import java.util.Map;

import com.nyayas.common.service.Service;

public interface CaseTypeService extends Service<CaseTypeService> {

	Map<String, String> param(Map<String, String> param);

	Map<String, Object> caseStatus(Map<String, String> param) throws IOException;
}

package com.nyayas.status.service.court;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.nyayas.status.service.CaseStatusByCaseTypeService;
import com.nyayas.status.service.CaseTypeService;

@Service
public class OrissaHighCourt extends CaseStatusByCaseTypeService {

	@Override
	public boolean supports(Class<CaseTypeService> clazz, Object id) {
		return false;
	}

	@Override
	public Map<String, Object> caseStatus(Map<String, String> param) throws IOException {
		return Collections.emptyMap();
	}
}

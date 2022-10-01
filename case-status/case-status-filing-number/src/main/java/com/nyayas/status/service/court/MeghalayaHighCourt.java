package com.nyayas.status.service.court;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.nyayas.status.service.CaseStatusByFilingNumberService;
import com.nyayas.status.service.FilingNumberService;

@Service
public class MeghalayaHighCourt extends CaseStatusByFilingNumberService {

	@Override
	public boolean supports(Class<FilingNumberService> clazz, Object id) {
		return false;
	}

	@Override
	public Map<String, Object> caseStatus(Map<String, String> param) throws IOException {
		return super.caseStatus(param);
	}
}

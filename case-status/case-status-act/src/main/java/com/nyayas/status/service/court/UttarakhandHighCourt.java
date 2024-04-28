package com.nyayas.status.service.court;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.nyayas.status.service.ActService;
import com.nyayas.status.service.CaseStatusByActService;

@Service
public class UttarakhandHighCourt extends CaseStatusByActService {

	@Override
	public boolean supports(Class<ActService> clazz, Object id) {
		return false;
	}

	@Override
	public Map<String, Object> caseStatus(Map<String, String> param) throws IOException {
		return Collections.emptyMap();
	}
}

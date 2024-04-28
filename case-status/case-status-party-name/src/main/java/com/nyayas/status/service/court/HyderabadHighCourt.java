package com.nyayas.status.service.court;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.nyayas.status.service.CaseStatusByPartyNameService;
import com.nyayas.status.service.PartyNameService;

@Service
public class HyderabadHighCourt extends CaseStatusByPartyNameService {

	@Override
	public boolean supports(Class<PartyNameService> clazz, Object id) {
		return false;
	}

	@Override
	public Map<String, Object> caseStatus(Map<String, String> param) throws IOException {
		return Collections.emptyMap();
	}
}

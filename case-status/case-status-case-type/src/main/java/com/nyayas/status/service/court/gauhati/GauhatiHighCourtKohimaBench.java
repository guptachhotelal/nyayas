package com.nyayas.status.service.court.gauhati;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.nyayas.status.service.CaseTypeService;
import com.nyayas.status.service.court.GauhatiHighCourt;

@Service
public class GauhatiHighCourtKohimaBench extends GauhatiHighCourt {

	@Override
	public boolean supports(Class<CaseTypeService> clazz, Object id) {
		return false;
	}

	@Override
	public Map<String, Object> caseStatus(Map<String, String> param) throws IOException {
		return Collections.emptyMap();
	}
}

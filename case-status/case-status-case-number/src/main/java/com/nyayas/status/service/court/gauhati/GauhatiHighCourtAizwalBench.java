package com.nyayas.status.service.court.gauhati;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.nyayas.status.service.CaseNumberService;
import com.nyayas.status.service.court.GauhatiHighCourt;

@Service
public class GauhatiHighCourtAizwalBench extends GauhatiHighCourt {

	@Override
	public boolean supports(Class<CaseNumberService> clazz, Object id) {
		return false;
	}

	@Override
	public Map<String, Object> byCaseNumber(Map<String, String> param) throws IOException {
		return Collections.emptyMap();
	}

}

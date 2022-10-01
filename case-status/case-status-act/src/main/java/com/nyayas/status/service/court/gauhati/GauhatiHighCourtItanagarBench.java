package com.nyayas.status.service.court.gauhati;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.nyayas.status.service.ActService;
import com.nyayas.status.service.court.GauhatiHighCourt;

@Service
public class GauhatiHighCourtItanagarBench extends GauhatiHighCourt {

	@Override
	public boolean supports(Class<ActService> clazz, Object id) {
		return false;
	}

	@Override
	public Map<String, Object> caseStatus(Map<String, String> param) throws IOException {
		return super.caseStatus(param);
	}
}

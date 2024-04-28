package com.nyayas.status.service.court.rajasthan;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.nyayas.status.service.FIRNumberService;
import com.nyayas.status.service.court.RajathanHighCourt;

@Service
public class RajathanHighCourtJodhpurBench extends RajathanHighCourt {

	@Override
	public boolean supports(Class<FIRNumberService> clazz, Object id) {
		return false;
	}

	@Override
	public Map<String, Object> caseStatus(Map<String, String> param) throws IOException {
		return Collections.emptyMap();
	}
}

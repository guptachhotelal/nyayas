package com.nyayas.status.service.court.madras;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.nyayas.status.service.PartyNameService;
import com.nyayas.status.service.court.MadrasHighCourt;

@Service
public class MadrasHighCourtMadrasBench extends MadrasHighCourt {

	@Override
	public boolean supports(Class<PartyNameService> clazz, Object id) {
		return false;
	}

	@Override
	public Map<String, Object> caseStatus(Map<String, String> param) throws IOException {
		return Collections.emptyMap();
	}
}

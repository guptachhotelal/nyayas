package com.nyayas.status.service.court.madras;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.nyayas.status.service.CaseStatusByFIRNumberService;
import com.nyayas.status.service.FIRNumberService;

@Service
public class MadrasHighCourtMaduraiBench extends CaseStatusByFIRNumberService {

	@Override
	public boolean supports(Class<FIRNumberService> clazz, Object id) {
		return false;
	}

	@Override
	public Map<String, Object> caseStatus(Map<String, String> param) throws IOException {
		return Collections.emptyMap();
	}

}

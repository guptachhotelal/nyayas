package com.nyayas.status.service.court.bombay;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.nyayas.common.constant.Courts;
import com.nyayas.status.service.FIRNumberService;
import com.nyayas.status.service.court.BombayHighCourt;

@Service
public class BombayHighCourtGoaBench extends BombayHighCourt {
	
	@Override
	public boolean supports(Class<FIRNumberService> clazz, Object id) {
		return FIRNumberService.class.equals(clazz) && id.equals(Courts.BOMBAY_HIGH_COURT_GOA.code());
	}

	@Override
	public Map<String, Object> caseStatus(Map<String, String> param) throws IOException {
		return Collections.emptyMap();
	}
}

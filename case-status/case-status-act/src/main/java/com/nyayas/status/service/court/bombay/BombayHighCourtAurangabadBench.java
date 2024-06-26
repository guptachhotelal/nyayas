package com.nyayas.status.service.court.bombay;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.nyayas.common.constant.Courts;
import com.nyayas.status.service.ActService;
import com.nyayas.status.service.court.BombayHighCourt;

@Service
public class BombayHighCourtAurangabadBench extends BombayHighCourt {
	
	@Override
	public boolean supports(Class<ActService> clazz, Object id) {
		return ActService.class.equals(clazz) && id.equals(Courts.BOMBAY_HIGH_COURT_AURANGABAD.code());
	}

	@Override
	public Map<String, Object> caseStatus(Map<String, String> param) throws IOException {
		return  Collections.emptyMap();
	}
}

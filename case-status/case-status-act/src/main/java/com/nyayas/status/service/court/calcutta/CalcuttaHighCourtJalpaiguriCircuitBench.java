package com.nyayas.status.service.court.calcutta;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.nyayas.status.service.ActService;
import com.nyayas.status.service.court.CalcuttaHighCourt;

@Service
public class CalcuttaHighCourtJalpaiguriCircuitBench extends CalcuttaHighCourt {

	@Override
	public boolean supports(Class<ActService> clazz, Object id) {
		return false;
	}

	@Override
	public Map<String, Object> caseStatus(Map<String, String> param) throws IOException {
		return super.caseStatus(param);
	}
}

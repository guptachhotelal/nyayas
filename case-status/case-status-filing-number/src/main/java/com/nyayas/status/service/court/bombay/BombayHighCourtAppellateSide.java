package com.nyayas.status.service.court.bombay;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.nyayas.status.service.FilingNumberService;
import com.nyayas.status.service.court.BombayHighCourt;

@Service
public class BombayHighCourtAppellateSide extends BombayHighCourt {

	@Override
	public boolean supports(Class<FilingNumberService> clazz, Object id) {
		return false;
	}

	@Override
	public Map<String, Object> caseStatus(Map<String, String> param) throws IOException {
		return super.caseStatus(param);
	}
}

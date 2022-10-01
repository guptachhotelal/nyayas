package com.nyayas.status.service.court.bombay;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.nyayas.common.constant.Courts;
import com.nyayas.status.service.CaseTypeService;
import com.nyayas.status.service.court.BombayHighCourt;

@Service
public class BombayHighCourtGoaBench extends BombayHighCourt {
	
	@Override
	public boolean supports(Class<CaseTypeService> clazz, Object id) {
		return CaseTypeService.class.equals(clazz) && id.equals(Courts.BOMBAY_HIGH_COURT_GOA.courtId());
	}

	@Override
	public Map<String, Object> caseStatus(Map<String, String> param) throws IOException {
		return super.caseStatus(param);
	}
}

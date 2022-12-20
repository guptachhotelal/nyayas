package com.nyayas.status.service.court.bombay;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.nyayas.common.constant.Courts;
import com.nyayas.status.service.CaseNumberService;
import com.nyayas.status.service.court.BombayHighCourt;

@Service
public class BombayHighCourtAurangabadBench extends BombayHighCourt {
	
	@Override
	public boolean supports(Class<CaseNumberService> clazz, Object id) {
		return CaseNumberService.class.equals(clazz) && id.equals(Courts.BOMBAY_HIGH_COURT_AURANGABAD.code());
	}

	@Override
	public Map<String, Object> byCaseNumber(Map<String, String> param) throws IOException {
		return super.byCaseNumber(param);
	}
}

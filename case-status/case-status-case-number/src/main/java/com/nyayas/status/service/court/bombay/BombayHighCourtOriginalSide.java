package com.nyayas.status.service.court.bombay;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.nyayas.status.service.CaseNumberService;
import com.nyayas.status.service.court.BombayHighCourt;

@Service
public class BombayHighCourtOriginalSide extends BombayHighCourt {
	
	@Override
	public boolean supports(Class<CaseNumberService> clazz, Object id) {
		return false;
	}

	@Override
	public Map<String, Object> byCaseNumber(Map<String, String> param) throws IOException {
		return super.byCaseNumber(param);
	}
}

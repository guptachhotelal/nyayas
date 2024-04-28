package com.nyayas.status.service.court.bombay;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.nyayas.common.constant.Courts;
import com.nyayas.status.service.CINCNRService;
import com.nyayas.status.service.court.BombayHighCourt;

@Service
public class BombayHighCourtNagpurBench extends BombayHighCourt {

	@Override
	public boolean supports(Class<CINCNRService> clazz, Object id) {
		return CINCNRService.class.equals(clazz) && id.equals(Courts.BOMBAY_HIGH_COURT_NAGPUR.code());
	}

	@Override
	public Map<String, Object> byCINCNR(Map<String, String> param) throws IOException {
		return Collections.emptyMap();
	}
}

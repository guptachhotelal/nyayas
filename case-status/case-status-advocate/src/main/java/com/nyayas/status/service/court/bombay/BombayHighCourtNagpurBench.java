package com.nyayas.status.service.court.bombay;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.nyayas.common.constant.Courts;
import com.nyayas.status.service.AdvocateService;
import com.nyayas.status.service.court.BombayHighCourt;

@Service
public class BombayHighCourtNagpurBench extends BombayHighCourt {
	
	@Override
	public boolean supports(Class<AdvocateService> clazz, Object id) {
		return AdvocateService.class.equals(clazz) && id.equals(Courts.BOMBAY_HIGH_COURT_NAGPUR.courtCode());
	}

	@Override
	public Map<String, Object> byAdvocateName(Map<String, String> param) throws IOException {
		return super.byAdvocateName(param);
	}
}

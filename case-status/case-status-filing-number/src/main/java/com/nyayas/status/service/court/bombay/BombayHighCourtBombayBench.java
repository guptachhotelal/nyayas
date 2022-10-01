package com.nyayas.status.service.court.bombay;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.nyayas.common.constant.Courts;
import com.nyayas.status.service.FilingNumberService;
import com.nyayas.status.service.court.BombayHighCourt;

@Service
public class BombayHighCourtBombayBench extends BombayHighCourt {

	@Override
	public boolean supports(Class<FilingNumberService> clazz, Object id) {
		return FilingNumberService.class.equals(clazz) && id.equals(Courts.BOMBAY_HIGH_COURT_BOMBAY.courtId());
	}

	@Override
	public Map<String, String> param(Map<String, String> param) {
		Map<String, String> map = super.param(param);
		map.put("m_hc", "01");
		return map;
	}

	@Override
	public Map<String, Object> caseStatus(Map<String, String> param) throws IOException {
		return super.caseStatus(param);
	}
}

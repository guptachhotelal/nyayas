package com.nyayas.status.service.court.calcutta;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.nyayas.status.service.AdvocateService;
import com.nyayas.status.service.court.CalcuttaHighCourt;

@Service
public class CalcuttaHighCourtOriginalSide extends CalcuttaHighCourt {

	@Override
	public boolean supports(Class<AdvocateService> clazz, Object id) {
		return false;
	}

	@Override
	public Map<String, Object> byAdvocateName(Map<String, String> param) throws IOException {
		return Collections.emptyMap();
	}
}

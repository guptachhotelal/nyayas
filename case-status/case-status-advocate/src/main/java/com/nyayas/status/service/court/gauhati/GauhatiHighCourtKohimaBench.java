package com.nyayas.status.service.court.gauhati;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.nyayas.status.service.AdvocateService;
import com.nyayas.status.service.court.GauhatiHighCourt;

@Service
public class GauhatiHighCourtKohimaBench extends GauhatiHighCourt {

	@Override
	public boolean supports(Class<AdvocateService> clazz, Object id) {
		return false;
	}

	@Override
	public Map<String, Object> byAdvocateName(Map<String, String> param) throws IOException {
		return super.byAdvocateName(param);
	}
}

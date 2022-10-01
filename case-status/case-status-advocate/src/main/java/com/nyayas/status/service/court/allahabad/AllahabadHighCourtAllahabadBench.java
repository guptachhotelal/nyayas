package com.nyayas.status.service.court.allahabad;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.nyayas.status.service.AdvocateService;
import com.nyayas.status.service.court.AllahabadHighCourt;

@Service
public class AllahabadHighCourtAllahabadBench extends AllahabadHighCourt {

	@Override
	public boolean supports(Class<AdvocateService> clazz, Object id) {
		return false;
	}

	@Override
	public Map<String, Object> byAdvocateName(Map<String, String> param) throws IOException {
		return super.byAdvocateName(param);
	}
}

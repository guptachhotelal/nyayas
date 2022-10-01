package com.nyayas.status.service.court.allahabad;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.nyayas.status.service.FilingNumberService;
import com.nyayas.status.service.court.AllahabadHighCourt;

@Service
public class AllahabadHighCourtAllahabadBench extends AllahabadHighCourt {

	@Override
	public boolean supports(Class<FilingNumberService> clazz, Object id) {
		return false;
	}

	@Override
	public Map<String, Object> caseStatus(Map<String, String> param) throws IOException {
		return super.caseStatus(param);
	}
}

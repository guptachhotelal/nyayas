package com.nyayas.status.service.court.karnataka;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.nyayas.status.service.FIRNumberService;
import com.nyayas.status.service.court.KarnatakaHighCourt;

@Service
public class KarntakaHighCourtBangaluruBench extends KarnatakaHighCourt {

	@Override
	public boolean supports(Class<FIRNumberService> clazz, Object id) {
		return false;
	}

	@Override
	public Map<String, Object> caseStatus(Map<String, String> param) throws IOException {
		return super.caseStatus(param);
	}
}

package com.nyayas.status.service.court.karnataka;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.nyayas.status.service.PartyNameService;
import com.nyayas.status.service.court.KarnatakaHighCourt;

@Service
public class KarntakaHighCourtDharwadBench extends KarnatakaHighCourt {

	@Override
	public boolean supports(Class<PartyNameService> clazz, Object id) {
		return false;
	}

	@Override
	public Map<String, Object> caseStatus(Map<String, String> param) throws IOException {
		return super.caseStatus(param);
	}
}

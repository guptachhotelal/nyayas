package com.nyayas.status.service.court.karnataka;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.nyayas.status.service.CINCNRService;
import com.nyayas.status.service.court.KarnatakaHighCourt;

@Service
public class KarntakaHighCourtBangaluruBench extends KarnatakaHighCourt {

	@Override
	public boolean supports(Class<CINCNRService> clazz, Object id) {
		return false;
	}

	@Override
	public Map<String, Object> byCINCNR(Map<String, String> param) throws IOException {
		return Collections.emptyMap();
	}
}

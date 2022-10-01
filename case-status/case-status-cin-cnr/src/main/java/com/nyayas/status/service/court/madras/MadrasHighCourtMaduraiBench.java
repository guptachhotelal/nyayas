package com.nyayas.status.service.court.madras;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.nyayas.status.service.CaseStatusByCINCNRService;
import com.nyayas.status.service.CINCNRService;

@Service
public class MadrasHighCourtMaduraiBench extends CaseStatusByCINCNRService {

	@Override
	public boolean supports(Class<CINCNRService> clazz, Object id) {
		return false;
	}

	@Override
	public Map<String, Object> byCINCNR(Map<String, String> param) throws IOException {
		return super.byCINCNR(param);
	}

}

package com.nyayas.status.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.nyayas.common.constant.CaseFields;
import com.nyayas.common.service.CourtServiceFactory;
import com.nyayas.status.service.CINCNRService;

import jakarta.annotation.Resource;

public class CaseStatusByCINCNRController extends AbstractCaseStatusController {

	@Resource
	private CourtServiceFactory<CINCNRService> cincnrFactory;

	@PostMapping("cnr")
	public ResponseEntity<Map<String, Object>> byCINCNR(@RequestBody Map<String, String> param) {
		CINCNRService ccs = cincnrFactory.getService(CINCNRService.class, param.get(CaseFields.COURT_ID));
		try {
			return ResponseEntity.ok(ccs.byCINCNR(ccs.param(param)));
		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(Collections.emptyMap());
		}
	}

}

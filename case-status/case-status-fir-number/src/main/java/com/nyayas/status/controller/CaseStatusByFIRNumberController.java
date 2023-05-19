package com.nyayas.status.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.nyayas.common.constant.CaseFields;
import com.nyayas.common.service.ServiceFactory;
import com.nyayas.status.service.FIRNumberService;

import jakarta.annotation.Resource;

public class CaseStatusByFIRNumberController {

	@Resource
	private ServiceFactory<FIRNumberService> firNumberFactory;

	@PostMapping("fir-number")
	public ResponseEntity<Map<String, Object>> byFIRNumber(@RequestBody Map<String, String> param) {
		FIRNumberService fir = firNumberFactory.getService(FIRNumberService.class, param.get(CaseFields.COURT_ID));
		try {
			return ResponseEntity.ok(fir.caseStatus(fir.param(param)));
		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(Collections.emptyMap());
		}
	}

}

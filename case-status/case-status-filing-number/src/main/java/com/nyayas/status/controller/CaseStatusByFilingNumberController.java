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
import com.nyayas.status.service.FilingNumberService;

import jakarta.annotation.Resource;

public class CaseStatusByFilingNumberController {

	@Resource
	private ServiceFactory<FilingNumberService> filingNumberFactory;

	@PostMapping("filing-number")
	public ResponseEntity<Map<String, Object>> byFilingNumber(@RequestBody Map<String, String> param) {
		FilingNumberService fns = filingNumberFactory.getService(FilingNumberService.class,
				param.get(CaseFields.COURT_ID));
		try {
			return ResponseEntity.ok(fns.caseStatus(fns.param(param)));
		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(Collections.emptyMap());
		}
	}
}

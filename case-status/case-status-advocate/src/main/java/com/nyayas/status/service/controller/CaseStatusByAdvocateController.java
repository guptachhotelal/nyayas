package com.nyayas.status.service.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nyayas.common.constant.CaseFields;
import com.nyayas.common.service.CourtServiceFactory;
import com.nyayas.status.service.AdvocateService;

import jakarta.annotation.Resource;

@RequestMapping("advocate")
public class CaseStatusByAdvocateController {

	@Resource
	private CourtServiceFactory<AdvocateService> advocateFactory;

	@PostMapping("name")
	public ResponseEntity<Map<String, Object>> byAdvocateName(@RequestBody Map<String, String> param) {
		AdvocateService as = advocateFactory.getService(AdvocateService.class, param.get(CaseFields.COURT_ID));
		try {
			return ResponseEntity.ok(as.byAdvocateName(as.advocateNameParam(param)));
		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(Collections.emptyMap());
		}
	}

	@PostMapping("bar-code")
	public ResponseEntity<Map<String, Object>> byBarCode(@RequestBody Map<String, String> param) {
		AdvocateService as = advocateFactory.getService(AdvocateService.class, param.get(CaseFields.COURT_ID));
		try {
			return ResponseEntity.ok(as.byBarCode(as.barCodeParam(param)));
		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(Collections.emptyMap());
		}
	}

	@PostMapping("case-list-date")
	public ResponseEntity<Map<String, Object>> byCaseListDate(@RequestBody Map<String, String> param) {
		AdvocateService as = advocateFactory.getService(AdvocateService.class, param.get(CaseFields.COURT_ID));
		try {
			return ResponseEntity.ok(as.byCaseListDate(as.caseListDateParam(param)));
		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(Collections.emptyMap());
		}
	}
}

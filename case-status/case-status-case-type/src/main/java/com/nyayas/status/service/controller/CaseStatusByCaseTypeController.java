package com.nyayas.status.service.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.nyayas.common.constant.CaseFields;
import com.nyayas.common.service.CourtServiceFactory;
import com.nyayas.status.controller.AbstractCaseStatusController;
import com.nyayas.status.service.CaseTypeService;

import jakarta.annotation.Resource;

public class CaseStatusByCaseTypeController extends AbstractCaseStatusController {

	@Resource
	private CourtServiceFactory<CaseTypeService> caseTypeFactory;

	@PostMapping("case-type")
	public ResponseEntity<Map<String, Object>> byPartyName(@RequestBody Map<String, String> param) {
		CaseTypeService pns = caseTypeFactory.getService(CaseTypeService.class, param.get(CaseFields.COURT_ID));
		try {
			return ResponseEntity.ok(pns.caseStatus(pns.param(param)));
		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(Collections.emptyMap());
		}
	}

}

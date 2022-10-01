package com.nyayas.status.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.nyayas.common.constant.CaseFields;
import com.nyayas.common.service.CourtServiceFactory;
import com.nyayas.status.service.ActService;

public class CaseStatusByActController extends AbstractCaseStatusController {

	@Resource
	private CourtServiceFactory<ActService> actFactory;

	@PostMapping("act")
	public ResponseEntity<Map<String, Object>> byPartyName(@RequestBody Map<String, String> param) {
		ActService pns = actFactory.getService(ActService.class, param.get(CaseFields.COURT_ID));
		try {
			return ResponseEntity.ok(pns.caseStatus(pns.param(param)));
		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(Collections.emptyMap());
		}
	}

}

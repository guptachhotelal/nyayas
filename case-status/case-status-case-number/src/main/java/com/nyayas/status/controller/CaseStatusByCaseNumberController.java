package com.nyayas.status.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nyayas.common.constant.APIConstants;
import com.nyayas.common.constant.CaseFields;
import com.nyayas.common.service.CourtServiceFactory;
import com.nyayas.common.util.ResponseUtil;
import com.nyayas.status.service.CaseNumberService;

import jakarta.annotation.Resource;

@RestController
@RequestMapping(APIConstants.CASE_STATUS_BASE)
public class CaseStatusByCaseNumberController {

	@Resource
	private CourtServiceFactory<CaseNumberService> caseNumberFactory;

	@PostMapping("case-number")
	public ResponseEntity<Map<String, Object>> byCaseNumber(@RequestBody Map<String, String> param) {
		CaseNumberService cns = caseNumberFactory.getService(CaseNumberService.class, param.get(CaseFields.COURT_ID));
		try {
			return ResponseEntity.ok(cns.byCaseNumber(cns.caseNumberParam(param)));
		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(ResponseUtil.data(param));
		}
	}
}

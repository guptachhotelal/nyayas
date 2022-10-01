package com.nyayas.causelist.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nyayas.causelist.service.CauseListService;
import com.nyayas.common.service.CourtServiceFactory;

@RestController
public class CauseListByJudgeController {

	@Resource
	private CourtServiceFactory<CauseListService> causeListFactory;

	@PostMapping("causeList")
	public ResponseEntity<Map<String, Object>> causeList(@RequestBody Map<String, String> param) {
		return null;
	}

}

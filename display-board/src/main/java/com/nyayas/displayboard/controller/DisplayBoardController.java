package com.nyayas.displayboard.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nyayas.common.service.CourtServiceFactory;
import com.nyayas.displayboard.service.DisplayBoardService;

@RestController
public class DisplayBoardController {

	@Resource
	private CourtServiceFactory<DisplayBoardService> displayBoardFactory;

	@PostMapping("causeList")
	public ResponseEntity<Map<String, Object>> displayBoard(@RequestBody Map<String, String> param) {
		return null;
	}
}

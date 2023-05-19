package com.nyayas.displayboard.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nyayas.common.service.ServiceFactory;
import com.nyayas.displayboard.service.DisplayBoardService;

import jakarta.annotation.Resource;

@RestController
public class DisplayBoardController {

	@Resource
	private ServiceFactory<DisplayBoardService> displayBoardFactory;

	@PostMapping("causeList")
	public ResponseEntity<Map<String, Object>> displayBoard(@RequestBody Map<String, String> param) {
		return null;
	}
}

package com.nyayas.causelist.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nyayas.causelist.service.CauseListService;
import com.nyayas.common.service.ServiceFactory;

import jakarta.annotation.Resource;

@RestController
public class CauseListEntireController {

    @Resource
    private ServiceFactory<CauseListService> causeListFactory;

    @PostMapping("entire")
    public ResponseEntity<Map<String, Object>> causeList(@RequestBody Map<String, String> param) {
	return null;
    }

}

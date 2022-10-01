package com.nyayas.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nyayas.common.util.FilterAndSortUtil;
import com.nyayas.service.DistrictCourtService;
import com.nyayas.service.HighCourtService;
import com.nyayas.service.vo.DistrictCourt;
import com.nyayas.service.vo.HighCourt;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RequestMapping("v1")
@RestController
public class CourtRestController {

	@Resource
	private HighCourtService highCourtService;

	@Resource
	private DistrictCourtService districtCourtService;

	@PostMapping("high-courts")
	public ResponseEntity<Map<String, Object>> highCourtList(HttpServletRequest request) {
		log.info("Listing high courts");
		String sortColumn = request.getParameter("order[0][column]");
		boolean asc = "asc".equals(request.getParameter("order[0][dir]")) ? true : false;
		String sText = request.getParameter("search[value]");
		sText = Objects.isNull(sText) ? "" : sText.toLowerCase();
		String sStart = request.getParameter("start");
		int start = Objects.isNull(sStart) ? 0 : Integer.parseInt(sStart);
		String sLength = request.getParameter("length");
		int length = Objects.isNull(sLength) ? 10 : Integer.parseInt(sLength);
		length = length <= -1 ? 100 : length;
		int pageNumber = start / length + 1;
		Map<String, Object> dataMap = new HashMap<>();
		List<HighCourt> list = new ArrayList<>();
		try {
			list.addAll(highCourtService.courts());
		} catch (IOException e) {
			log.info(e.getMessage());
		}
		dataMap.put("draw", request.getParameter("draw"));
		dataMap.put("recordsTotal", list.size());
		Map<Long, List<HighCourt>> fnsMap = FilterAndSortUtil.filterAndSort(list, sText, pageNumber, length, sortColumn,
				asc);
		fnsMap.entrySet().stream().forEach(entry -> {
			dataMap.put("recordsFiltered", entry.getKey());
			dataMap.put("data", entry.getValue());
		});
		return ResponseEntity.ok(dataMap);
	}

	@PostMapping("district-courts")
	public ResponseEntity<Map<String, Object>> districtCourtList(HttpServletRequest request) {
		log.info("Listing district courts");
		String sortColumn = request.getParameter("order[0][column]");
		boolean asc = "asc".equals(request.getParameter("order[0][dir]")) ? true : false;
		String sText = request.getParameter("search[value]");
		sText = Objects.isNull(sText) ? "" : sText.toLowerCase();
		String sStart = request.getParameter("start");
		int start = Objects.isNull(sStart) ? 0 : Integer.parseInt(sStart);
		String sLength = request.getParameter("length");
		int length = Objects.isNull(sLength) ? 10 : Integer.parseInt(sLength);
		length = length <= -1 ? 100 : length;
		int pageNumber = start / length + 1;
		Map<String, Object> dataMap = new HashMap<>();
		List<DistrictCourt> list = new ArrayList<>();
		try {
			list.addAll(districtCourtService.courts());
		} catch (IOException e) {
			log.info(e.getMessage());
		}
		dataMap.put("draw", request.getParameter("draw"));
		dataMap.put("recordsTotal", list.size());
		Map<Long, List<DistrictCourt>> fnsMap = FilterAndSortUtil.filterAndSort(list, sText, pageNumber, length,
				sortColumn, asc);
		fnsMap.entrySet().stream().forEach(entry -> {
			dataMap.put("recordsFiltered", entry.getKey());
			dataMap.put("data", entry.getValue());
		});
		return ResponseEntity.ok(dataMap);
	}
}
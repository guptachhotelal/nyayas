package com.nyayas.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nyayas.common.constant.Courts;
import com.nyayas.common.service.ServiceFactory;
import com.nyayas.common.util.FilterAndSortUtil;
import com.nyayas.service.CourtService;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("v1")
public class CourtController {

	@Resource
	private ServiceFactory<CourtService> courtServiceFactory;

	@PostMapping("courts")
	public ResponseEntity<Map<String, Object>> highCourtList(HttpServletRequest request) {
		String courtCode = request.getParameter("courtCode");
		courtCode = Objects.isNull(courtCode) ? Courts.ECOURT_HIGH_COURT.code() : courtCode.toUpperCase();
		String courtType = "hc";
		if ("ce".equals(request.getParameter("courtType"))) {
			courtType = "ce";
		} else if ("cc".equals(request.getParameter("courtType"))) {
			courtType = "cc";
		}
		log.info("Listing {} courts", Courts.fromCode(courtCode).text());
		String sortColumn = request.getParameter("order[0][column]");
		boolean asc = "asc".equals(request.getParameter("order[0][dir]")) || false;
		String sText = request.getParameter("search[value]");
		sText = Objects.isNull(sText) ? "" : sText.toLowerCase();
		String sStart = request.getParameter("start");
		int start = Objects.isNull(sStart) ? 0 : Integer.parseInt(sStart);
		String sLength = request.getParameter("length");
		int length = Objects.isNull(sLength) ? 10 : Integer.parseInt(sLength);
		length = length <= -1 ? 100 : length;
		int pageNumber = start / length + 1;
		Map<String, Object> dataMap = new HashMap<>();
		List<Serializable> list = new ArrayList<>();
		CourtService cs = courtServiceFactory.getService(CourtService.class, courtCode);
		try {
			list.addAll(cs.courts(courtType));
		} catch (IOException e) {
			log.info(e.getMessage());
		}
		dataMap.put("draw", request.getParameter("draw"));
		dataMap.put("recordsTotal", list.size());
		Map<Long, List<Serializable>> fnsMap = FilterAndSortUtil.filterAndSort(list, sText, pageNumber, length,
				sortColumn, asc);
		fnsMap.entrySet().stream().forEach(entry -> {
			dataMap.put("recordsFiltered", entry.getKey());
			dataMap.put("data", entry.getValue());
		});
		return ResponseEntity.ok(dataMap);
	}
}
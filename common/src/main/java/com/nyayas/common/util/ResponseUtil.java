package com.nyayas.common.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.nyayas.common.constant.CaseFields;

public final class ResponseUtil {
	public static final List<String> REQUEST_KEYS = Arrays.asList(CaseFields.CASE_NUMBER, CaseFields.CASE_YEAR,
			CaseFields.CASE_TYPE, CaseFields.STATE_CODE, CaseFields.DISTRICT_CODE, CaseFields.CITY_CODE,
			CaseFields.COURT_CODE, CaseFields.CASE_PARTY_NAME);

	public static final Map<String, Object> data(Map<String, String> request) {
		return data(request, Collections.emptyList());
	}

	public static final Map<String, Object> data(Map<String, String> request, Object resposne) {
		Set<String> keys = request.keySet().stream().filter(key -> !REQUEST_KEYS.contains(key))
				.collect(Collectors.toSet());
		request.keySet().removeAll(keys);
		request.put("timestamp", String.valueOf(System.currentTimeMillis()));
		Map<String, Object> map = new LinkedHashMap<>();
		map.put("request", request);
		map.put("response", resposne);
		return map;
	}
}

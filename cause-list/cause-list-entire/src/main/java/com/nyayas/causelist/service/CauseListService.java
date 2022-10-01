package com.nyayas.causelist.service;

import java.util.Map;

import com.nyayas.common.service.Service;

public interface CauseListService extends Service<CauseListService> {
	Map<String, Object> causelist(Map<String, String> param);
}

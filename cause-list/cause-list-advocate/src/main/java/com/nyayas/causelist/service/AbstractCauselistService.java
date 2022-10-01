package com.nyayas.causelist.service;

import java.util.Collections;
import java.util.Map;

public abstract class AbstractCauselistService implements CauseListService {
	
	@Override
	public Map<String, Object> causelist(Map<String, String> param) {
		return Collections.emptyMap();
	}
}

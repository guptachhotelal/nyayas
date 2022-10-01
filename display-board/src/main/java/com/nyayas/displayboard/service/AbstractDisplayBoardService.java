package com.nyayas.displayboard.service;

import java.util.Collections;
import java.util.Map;

public abstract class AbstractDisplayBoardService implements DisplayBoardService {

	@Override
	public Map<String, Object> displayBoard(Map<String, String> param) {
		return Collections.emptyMap();
	}
}

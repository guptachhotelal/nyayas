package com.nyayas.displayboard.service.hc;

import java.util.Collections;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.nyayas.displayboard.service.AbstractDisplayBoardService;
import com.nyayas.displayboard.service.DisplayBoardService;

@Service
public class BombayHighCourt extends AbstractDisplayBoardService {

    @Override
    public boolean supports(Class<DisplayBoardService> clazz, Object id) {
	return false;
    }

    @Override
    public Map<String, Object> displayBoard(Map<String, String> param) {
	return Collections.emptyMap();
    }

}

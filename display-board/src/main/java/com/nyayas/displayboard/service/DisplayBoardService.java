package com.nyayas.displayboard.service;

import java.util.Map;

import com.nyayas.common.service.Service;

public interface DisplayBoardService extends Service<DisplayBoardService> {
    Map<String, Object> displayBoard(Map<String, String> param);
}

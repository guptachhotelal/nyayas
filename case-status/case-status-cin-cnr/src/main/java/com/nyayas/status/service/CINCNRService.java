package com.nyayas.status.service;

import java.io.IOException;
import java.util.Map;

import com.nyayas.common.service.Service;

public interface CINCNRService extends Service<CINCNRService> {

	Map<String, String> param(Map<String, String> param);

	Map<String, Object> byCINCNR(Map<String, String> param) throws IOException;
}

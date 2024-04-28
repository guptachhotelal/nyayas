package com.nyayas.order.service;

import java.io.IOException;
import java.util.Map;

import com.nyayas.common.service.Service;

public interface JudgeWiseService extends Service<JudgeWiseService> {

    Map<String, String> caseNumberParam(Map<String, String> param);

    Map<String, Object> byCaseNumber(Map<String, String> param) throws IOException;
}

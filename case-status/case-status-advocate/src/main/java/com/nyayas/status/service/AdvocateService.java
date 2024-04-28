package com.nyayas.status.service;

import java.io.IOException;
import java.util.Map;

import com.nyayas.common.service.Service;

public interface AdvocateService extends Service<AdvocateService> {

	Map<String, String> advocateNameParam(Map<String, String> param);

	Map<String, String> barCodeParam(Map<String, String> param);

	Map<String, String> caseListDateParam(Map<String, String> param);

	Map<String, Object> byAdvocateName(Map<String, String> param) throws IOException;

	Map<String, Object> byBarCode(Map<String, String> param) throws IOException;

	Map<String, Object> byCaseListDate(Map<String, String> param) throws IOException;

}

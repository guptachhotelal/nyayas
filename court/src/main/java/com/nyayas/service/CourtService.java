package com.nyayas.service;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.nyayas.common.service.Service;

public interface CourtService extends Service<CourtService> {

	Map<String, String> states() throws IOException;

	String states(String stateCode) throws IOException;

	List<Serializable> caseTypes() throws IOException;

	List<Serializable> courts(String courtType) throws IOException;
}
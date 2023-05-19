package com.nyayas.service;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import com.nyayas.common.service.Service;

public interface CourtService extends Service<CourtService> {
	
	List<Serializable> caseTypes() throws IOException;

	List<Serializable> courts() throws IOException;
}
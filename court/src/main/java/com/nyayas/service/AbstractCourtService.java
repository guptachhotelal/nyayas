package com.nyayas.service;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public abstract class AbstractCourtService implements CourtService {
	
	@Override
	public List<Serializable> caseTypes() throws IOException {
		return Collections.emptyList();
	}

	public List<Serializable> courts() throws IOException {
		return Collections.emptyList();
	}
}

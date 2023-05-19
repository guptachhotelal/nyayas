package com.nyayas.service;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractCourtService implements CourtService {

	private static final Map<Integer, String> STATES = new HashMap<>();

	@Override
	public List<Serializable> caseTypes() throws IOException {
		return Collections.emptyList();
	}

	public List<Serializable> courts() throws IOException {
		return Collections.emptyList();
	}

	protected static String states(int stateCode) {
		if (STATES.containsKey(stateCode)) {
			return STATES.get(stateCode);
		}
		STATES.put(1, "Maharashtra");
		STATES.put(2, "Andhra Pradesh");
		STATES.put(3, "Karnataka");
		STATES.put(4, "Kerala");
		STATES.put(5, "Himachal Pradesh");
		STATES.put(6, "Assam");
		STATES.put(7, "Jharkhand");
		STATES.put(8, "Bihar");
		STATES.put(9, "Rajasthan");
		STATES.put(10, "Tamil Nadu");
		STATES.put(11, "Orissa");
		STATES.put(12, "Jammu and Kashmir");
		STATES.put(13, "Uttar Pradesh");
		STATES.put(14, "");
		STATES.put(15, "West Bengal");
		STATES.put(15, "Uttarakhand");
		STATES.put(16, "West Bengal ");
		STATES.put(17, "Gujarat");
		STATES.put(18, "Chhattisgarh");
		STATES.put(19, "");
		STATES.put(20, "Tripura");
		STATES.put(21, "Meghalaya");
		STATES.put(22, "Punjab and Haryana");
		STATES.put(23, "Madhya Pradesh");
		STATES.put(24, "Sikkim");
		STATES.put(25, "Manipur");
		return STATES.get(stateCode);
	}
}

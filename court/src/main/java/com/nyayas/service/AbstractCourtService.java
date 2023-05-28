package com.nyayas.service;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.nyayas.common.util.JSoupHelper;

public abstract class AbstractCourtService implements CourtService {

	private static final String HOME_URL = "https://services.ecourts.gov.in/ecourtindia_v6/?p=casestatus/index";

	private static final Map<String, String> STATES = new HashMap<>();

	@Override
	public List<Serializable> caseTypes() throws IOException {
		return Collections.emptyList();
	}

	@Override
	public Map<String, String> states() throws IOException {
		Document doc = JSoupHelper.getResponse(HOME_URL, 0).parse();
		Elements state = doc.getElementById("sess_state_code").getElementsByTag("option");
		return state.stream().skip(1).collect(Collectors.toMap(Element::val, Element::text));
	}

	@Override
	public String states(String stateCode) throws IOException {
		if (STATES.isEmpty()) {
			STATES.putAll(states());
		}
		return STATES.get(stateCode);
	}

}

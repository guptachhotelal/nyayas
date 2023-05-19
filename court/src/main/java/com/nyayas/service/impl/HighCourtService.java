package com.nyayas.service.impl;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.nyayas.common.constant.Courts;
import com.nyayas.common.util.JSoupHelper;
import com.nyayas.service.AbstractCourtService;
import com.nyayas.service.CourtService;
import com.nyayas.service.vo.HighCourt;

//https://curl.trillworks.com
@Service
public class HighCourtService extends AbstractCourtService {

	private static final String HOME_URL = "https://hcservices.ecourts.gov.in/hcservices/main.php";
	private static final String COURT_URL = "https://hcservices.ecourts.gov.in/hcservices/cases_qry/index_qry.php";
	// https://services.ecourts.gov.in/ecourtindiaHC/cases/case_no.php?state_cd=1&dist_cd=1&court_code=1&stateNm=Bombay


	private static final List<Serializable> HIGH_COURTS = new ArrayList<>();

	@Override
	public boolean supports(Class<CourtService> clazz, Object id) {
		return CourtService.class.equals(clazz) && id.equals(Courts.ECOURT_HIGH_COURT.code());
	}

	public List<Serializable> courts() throws IOException {
		if (!HIGH_COURTS.isEmpty()) {
			return HIGH_COURTS;
		}
		Response response = JSoupHelper.getResponse(HOME_URL, 0);
		Map<String, String> cookies = response.cookies();
		Document doc = response.parse();
		String html = doc.html();
		String csrfToken = html.substring(html.indexOf("sid:"), html.indexOf(";var") - 1);
		Elements states = doc.getElementById("sess_state_code").children();
		Map<String, String> data = new HashMap<>();
		data.put("appFlag", "web");
		data.put("action_code", "fillHCBench");
		data.put("__csrf_magic", csrfToken);

		List<HighCourt> hcs = new ArrayList<>();
		for (int i = 1; i < states.size(); i++) {
			Element state = states.get(i);
			data.put("state_code", state.val());
			Response resp = JSoupHelper.postConnection(COURT_URL, 0).cookies(cookies).data(data).execute();
			String respString = resp.body();
			String[] parts = respString.split("#");
			for (int j = 1; j < parts.length; j++) {
				String[] bench = parts[j].split("~");
				hcs.add(HighCourt.highCourtBuilder().stateCode(Integer.parseInt(state.val()))
						.stateName(states(Integer.parseInt(state.val()))).courtName(state.text())
						.benchCode(Integer.parseInt(bench[0])).benchName(bench[1]).build());
			}
		}

		HIGH_COURTS.addAll(
				hcs.stream().sorted(Comparator.comparingInt(hc -> hc.getStateCode())).collect(Collectors.toList()));

		return HIGH_COURTS;
	}
}

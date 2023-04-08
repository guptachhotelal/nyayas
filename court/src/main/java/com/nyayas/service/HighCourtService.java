package com.nyayas.service;

import java.io.IOException;
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

import com.nyayas.common.util.JSoupHelper;
import com.nyayas.service.vo.HighCourt;

//https://curl.trillworks.com
@Service
public class HighCourtService {

	private static final String HOME_URL = "https://hcservices.ecourts.gov.in/hcservices/main.php";
	private static final String COURT_URL = "https://hcservices.ecourts.gov.in/hcservices/cases_qry/index_qry.php";
	// https://services.ecourts.gov.in/ecourtindiaHC/cases/case_no.php?state_cd=1&dist_cd=1&court_code=1&stateNm=Bombay

	private static final List<HighCourt> HIGH_COURTS = new ArrayList<>();

	public List<HighCourt> courts() throws IOException {
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
//				hcs.add(new HighCourt(Integer.parseInt(state.val()), states(Integer.parseInt(state.val())),
//						state.text(), Integer.parseInt(bench[0]), bench[1]));
			}
		}

		HIGH_COURTS.addAll(
				hcs.stream().sorted(Comparator.comparingInt(hc -> hc.getStateCode())).collect(Collectors.toList()));

		return HIGH_COURTS;
	}

	private static String states(int stateCode) {
		Map<Integer, String> map = new HashMap<>();
		map.put(1, "Maharashtra");
		map.put(2, "Andhra Pradesh");
		map.put(3, "Karnataka");
		map.put(4, "Kerala");
		map.put(5, "Himachal Pradesh");
		map.put(6, "Assam");
		map.put(7, "Jharkhand");
		map.put(8, "Bihar");
		map.put(9, "Rajasthan");
		map.put(10, "Tamil Nadu");
		map.put(11, "Orissa");
		map.put(12, "Jammu and Kashmir");
		map.put(13, "Uttar Pradesh");
		map.put(14, "");
		map.put(15, "West Bengal");
		map.put(15, "Uttarakhand");
		map.put(16, "West Bengal ");
		map.put(17, "Gujarat");
		map.put(18, "Chhattisgarh");
		map.put(19, "");
		map.put(20, "Tripura");
		map.put(21, "Meghalaya");
		map.put(22, "Punjab and Haryana");
		map.put(23, "Madhya Pradesh");
		map.put(24, "Sikkim");
		map.put(25, "Manipur");
		map.put(25, "Manipur");
		map.put(25, "Manipur");
		map.put(25, "Manipur");
		map.put(25, "Manipur");

		return map.get(stateCode);
	}

	public static void main(String[] args) throws Exception {
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
			System.out.println(respString);
			String[] parts = respString.split("#");
			for (int j = 1; j < parts.length; j++) {
				String[] bench = parts[j].split("~");
				hcs.add(HighCourt.highCourtBuilder().stateCode(Integer.parseInt(state.val()))
						.stateName(states(Integer.parseInt(state.val()))).courtName(state.text())
						.benchCode(Integer.parseInt(bench[0])).benchName(bench[1]).build());
			}
		}
		hcs.stream().forEach(System.out::println);
	}

}

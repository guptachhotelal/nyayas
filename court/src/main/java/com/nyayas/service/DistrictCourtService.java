package com.nyayas.service;

import java.util.HashMap;
import java.util.Map;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nyayas.common.constant.Courts;
import com.nyayas.common.util.JSoupHelper;

//https://curl.trillworks.com
@Service
public class DistrictCourtService extends AbstractCourtService {

	private static final String HOME_URL = "https://services.ecourts.gov.in/ecourtindia_v6/main.php";
	private static final String COURT_URL = "https://services.ecourts.gov.in/ecourtindia_v6/cases_qry/index_qry.php";

	@Override
	public boolean supports(Class<CourtService> clazz, Object id) {
		return CourtService.class.equals(clazz) && id.equals(Courts.ECOURT_DISTRICT_COURT.code());
	}

	public static void main(String[] args) throws Exception {
		Response response = JSoupHelper.getResponse(HOME_URL, 0);
		Map<String, String> cookies = response.cookies();
		response = JSoupHelper.getConnection(HOME_URL, 0).cookies(cookies).execute();
		cookies.putAll(response.cookies());
		String html = response.parse().html();
		String csrfToken = html.substring(html.indexOf("sid:"), html.indexOf(";var") - 1);
		Map<String, String> data = new HashMap<>();
		data.put("lang_code", "english");
		data.put("action_code", "fillLangState");
		data.put("__csrf_magic", csrfToken);
		Response resp = JSoupHelper.postConnection(COURT_URL, 0).cookies(cookies).referrer(HOME_URL).data(data)
				.execute();
		JsonNode json = new ObjectMapper().readValue(resp.body().replace("\uFEFF", ""), JsonNode.class);
		Document states = Jsoup.parseBodyFragment(json.get("selState").asText());
		Elements elements = states.getElementById("sess_state_code").children();
		elements.stream().skip(1).forEach(e -> System.out.println(e.attr("value") + "\t" + e.text()));
	}
}

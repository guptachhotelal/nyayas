package com.nyayas.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nyayas.common.util.JSoupHelper;

//https://curl.trillworks.com
//https://curlconverter.com/
@Service
public abstract class AbstractDistrictCourtService extends AbstractCourtService implements CourtTypeService {

    public static final String DISTRICT_URL = "https://services.ecourts.gov.in/ecourtindia_v6/?p=casestatus/fillDistrict";

    public static final String COURT_COMPLEX_URL = "https://services.ecourts.gov.in/ecourtindia_v6/?p=casestatus/fillcomplex";

    public static final String COURT_ESTABLISHMENT_URL = "https://services.ecourts.gov.in/ecourtindia_v4_bilingual/cases/case_no.php?state=D&state_cd=##sc##&dist_cd=##dc##";

    @Override
    public Map<String, String> districts(String stateCode) throws IOException {
	Map<String, String> param = new HashMap<>();
	param.put("ajax_req", "true");
	param.put("state_code", stateCode);

	Response distResp = JSoupHelper.postConnection(DISTRICT_URL, 0).data(param).execute();
	JsonNode distJson = new ObjectMapper().readValue(distResp.body().replace("\uFEFF", ""), JsonNode.class);
	String distHtml = distJson.get("dist_list").asText();
	Document districtDoc = Jsoup.parseBodyFragment(distHtml);
	Elements districts = districtDoc.getElementsByTag("option");
	return districts.stream().skip(1).collect(Collectors.toMap(Element::val, Element::text));
    }

    @Override
    public Map<String, String> courtComplexes(String stateCode, String districtCode) throws IOException {
	Map<String, String> param = new HashMap<>();
	param.put("ajax_req", "true");
	param.put("state_code", stateCode);
	param.put("dist_code", districtCode);

	Map<String, String> headers = new HashMap<>();
	headers.put("Sec-Fetch-Mode", "cors");
	headers.put("Sec-Fetch-Site", "same-origin");

	Response ccResp = JSoupHelper.postResponse(COURT_COMPLEX_URL, headers, param, 0);
	JsonNode ccJson = new ObjectMapper().readValue(ccResp.body().replace("\uFEFF", ""), JsonNode.class);
	String ccHtml = ccJson.get("complex_list").asText();
	Document ccDoc = Jsoup.parseBodyFragment(ccHtml);
	Elements cc = ccDoc.getElementsByTag("option");
	return cc.stream().skip(1).collect(Collectors.toMap(Element::val, Element::text));
    }

    @Override
    public Map<String, String> courtEstablishments(String stateCode, String districtCode) throws IOException {
	String url = COURT_ESTABLISHMENT_URL.replaceAll("##sc##", stateCode).replaceAll("##dc##", districtCode);
	Document ceDoc = JSoupHelper.getResponse(url, 0).parse();
	Elements courts = ceDoc.getElementById("court_code").getElementsByTag("option");
	return courts.stream().skip(1).collect(Collectors.toMap(Element::val, Element::text));
    }
}

package com.nyayas.common.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.nyayas.common.constant.CaseFields;

public class ECourtHelper {

    public static final Map<String, Object> caseDetail(Document sdDoc) {
	Map<String, Object> map = new HashMap<>();
	Elements caseDetails = sdDoc.getElementsByClass("case_details_table");
	for (Element cd : caseDetails) {
	    String text = cd.text().trim();
	    if (text.startsWith("Case Type")) {
		String[] parts = text.split(":");
		map.put(CaseFields.CASE_TYPE, parts[1].trim());
	    } else if (text.startsWith("Filing Number")) {
		String[] parts = text.split(":");
		map.put(CaseFields.FILING_NUMBER, parts[1].replaceAll("[a-zA-Z]", "").trim());
		map.put(CaseFields.FILING_DATE, parts[2].replaceAll(":", "").trim());
	    } else if (text.startsWith("Registration Number")) {
		String[] parts = text.split(":");
		map.put(CaseFields.REG_NUMBER, parts[1].replaceAll("[a-zA-Z]", "").trim());
		map.put(CaseFields.REG_DATE, parts[2].replaceAll(":", "").trim());
	    } else if (text.startsWith("CNR Number")) {
		String[] parts = text.split(":");
		map.put(CaseFields.CIN_NUMBER, parts[1]);
	    }
	}
	return map;
    }

    public static final Map<String, Object> caseStatus(Document sdDoc) {
	Elements caseStatus = sdDoc.select("label>strong");
	if (caseStatus.size() % 2 != 0) {
	    return Collections.emptyMap();
	}
	Map<String, Object> map = new HashMap<>();
	for (int i = 0; i < caseStatus.size(); i += 2) {
	    String key = caseStatus.get(i).text().trim();
	    String value = caseStatus.get(i + 1).text().replaceAll(":", "").trim();

	    switch (key) {
	    case "First Hearing Date":
		map.put(CaseFields.FIRST_HEARING_DATE, value);
		break;
	    case "Next Hearing Date":
		map.put(CaseFields.NEXT_HEARING_DATE, value);
		break;
	    case "Decision Date":
		map.put(CaseFields.DECISION_DATE, value);
		break;
	    case "Case Status":
	    case "Stage of Case":
		map.put(CaseFields.CASE_STAGE, value);
		break;
	    case "Nature of Disposal":
		map.put(CaseFields.DISPOSAL_NATURE, value);
		break;
	    case "Court Number and Judge":
		map.put(CaseFields.COURT_NUMBER_JUDGE, value);
		break;
	    case "Coram":
		map.put(CaseFields.CORAM, value);
		break;
	    case "Bench":
		map.put(CaseFields.BENCH, value);
		break;
	    case "State":
		map.put(CaseFields.STATE, value);
		break;
	    case "District":
		map.put(CaseFields.DISTRICT, value);
		break;
	    case "Judicial":
		map.put(CaseFields.JUDICIAL, value);
		break;
	    case "Causelist Name":
		map.put(CaseFields.CAUSELIST_NAME, value);
		break;
	    }
	}
	return map;
    }
}

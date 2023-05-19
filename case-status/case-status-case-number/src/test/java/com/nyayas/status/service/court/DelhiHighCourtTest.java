package com.nyayas.status.service.court;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nyayas.common.constant.CaseFields;
import com.nyayas.common.constant.Courts;
import com.nyayas.status.CaseStatusByCaseNumberTest;
import com.nyayas.status.service.CaseNumberService;

class DelhiHighCourtTest extends CaseStatusByCaseNumberTest {

	@BeforeEach
	public void setUp() {
		cns = caseNumberFactory.getService(CaseNumberService.class, Courts.DELHI_HIGH_COURT.code());
	}

	@Test
	void testSupports() {
		assertTrue(cns.supports(CaseNumberService.class, Courts.DELHI_HIGH_COURT.code()));
	}

	@Test
	void testCaseNumberParam() {
		Map<String, String> map = requestMap();
		Map<String, String> param = cns.caseNumberParam(map);
		assertTrue(param.get("cno").equals(map.get(CaseFields.CASE_NUMBER)));
		assertTrue(param.get("ctype").equals(map.get(CaseFields.CASE_TYPE)));
		assertTrue(param.get("cyear").equals(map.get(CaseFields.CASE_YEAR)));
	}

	@Test
	void testByCaseNumber() throws Exception {
		Map<String, String> param = cns.caseNumberParam(requestMap());
		String resp = new ObjectMapper().writeValueAsString(cns.byCaseNumber(param));
		assertTrue(resp.contains(CaseFields.CASE_TITLE));
		assertTrue(resp.contains(CaseFields.CASE_STAGE));
	}

	@Test
	void testStatusCaeNumber() throws Exception {
		String requestBody = mapper.writeValueAsString(requestMap());
		RequestBuilder builder = post(uri()).content(requestBody).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(builder).andDo(MockMvcResultHandlers.print()).andExpect(status().isOk());
	}

	private Map<String, String> requestMap() {
		Map<String, String> map = new HashMap<>();
		map.put(CaseFields.CASE_NUMBER, "1");
		map.put(CaseFields.CASE_TYPE, "ARB. A. (COMM.)");
		map.put(CaseFields.CASE_YEAR, "2022");
		map.put(CaseFields.COURT_ID, Courts.DELHI_HIGH_COURT.code());
		return map;
	}
}

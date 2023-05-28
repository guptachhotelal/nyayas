package com.nyayas.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.nyayas.CourtServiceApplicationTest;
import com.nyayas.common.constant.Courts;
import com.nyayas.service.impl.DistrictCourtService;

class DistrictCourtServiceTest extends CourtServiceApplicationTest {

	@BeforeEach
	void before() {
		cs = serviceFactory.getService(CourtService.class, Courts.ECOURT_DISTRICT_COURT.code());
	}

	@Test
	void testSupportsECHC() {
		boolean supports = cs.supports(CourtService.class, Courts.ECOURT_HIGH_COURT.code());
		assertFalse(supports);
	}

	@Test
	void testSupportsECDC() {
		assertTrue(supports());
	}

	void testCourts() throws Exception {
		MultiValueMap<String, String> param = new LinkedMultiValueMap<>();
		param.add("cc", "ECDC");
		param.add("order[0][column]", "complexName");
		RequestBuilder builder = post(uri()).params(param);
		mockMvc.perform(builder).andDo(MockMvcResultHandlers.print()).andExpect(status().isOk());
	}

	@Test
	void testStates() throws Exception {
		if (supports()) {
			DistrictCourtService dcs = (DistrictCourtService) cs;
			Map<String, String> states = dcs.states();
			assertNotNull(states);
			assertFalse(states.isEmpty());
		} else {
			fail("Service mismatch");
		}
	}

	@Test
	void testDistrict() throws Exception {
		if (supports()) {
			DistrictCourtService dcs = (DistrictCourtService) cs;
			Map<String, String> districts = dcs.districts("1");
			assertNotNull(districts);
			assertFalse(districts.isEmpty());
		} else {
			fail("Service mismatch");
		}
	}

	@Test
	void testCourtComplex() throws Exception {
		if (supports()) {
			DistrictCourtService dcs = (DistrictCourtService) cs;
			Map<String, String> courtComplexes = dcs.courtComplexes("1", "26");
			assertNotNull(courtComplexes);
			assertFalse(courtComplexes.isEmpty());
		} else {
			fail("Service mismatch");
		}
	}

	private boolean supports() {
		return cs.supports(CourtService.class, Courts.ECOURT_DISTRICT_COURT.code());
	}
}

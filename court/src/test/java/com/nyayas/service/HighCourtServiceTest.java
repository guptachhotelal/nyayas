package com.nyayas.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.nyayas.CourtServiceApplicationTest;
import com.nyayas.common.constant.Courts;

class HighCourtServiceTest extends CourtServiceApplicationTest {

    @BeforeEach
    void before() {
	cs = serviceFactory.getService(CourtService.class, Courts.ECOURT_HIGH_COURT.code());
    }

//	@Test
    void testSupportsECHC() {
	boolean supports = cs.supports(CourtService.class, Courts.ECOURT_HIGH_COURT.code());
	assertTrue(supports);
    }

//	@Test
    void testSupportsECDC() {
	boolean supports = cs.supports(CourtService.class, Courts.ECOURT_DISTRICT_COURT.code());
	assertFalse(supports);
    }

    // @Test
    void testCourts() throws Exception {
	MultiValueMap<String, String> param = new LinkedMultiValueMap<>();
	param.add("cc", "ECHC");
	param.add("order[0][column]", "benchCode");
	param.add("courtType", "hc");
	RequestBuilder builder = post(uri()).params(param);
	mockMvc.perform(builder).andDo(MockMvcResultHandlers.print()).andExpect(status().isOk());
    }
}

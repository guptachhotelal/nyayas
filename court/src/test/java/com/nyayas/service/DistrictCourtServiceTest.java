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

    // @Test
    void testCourtComplexCourts() throws Exception {
	testCourts("complexName", "cc");
    }

    // @Test
    void testCourtEstablishmentCourts() throws Exception {
	testCourts("establishmentName", "ce");
    }

    // This takes long time to eexecute, so disabled
    private void testCourts(String sortColumn, String courtType) throws Exception {
	MultiValueMap<String, String> param = new LinkedMultiValueMap<>();
	param.add("cc", "ECDC");
	param.add("order[0][column]", sortColumn);
	param.add("courtType", courtType);
	RequestBuilder builder = post(uri()).params(param);
	mockMvc.perform(builder).andDo(MockMvcResultHandlers.print()).andExpect(status().isOk());
    }

    @Test
    void testStates() throws Exception {
	if (supports()) {
	    Map<String, String> states = ((DistrictCourtService) cs).states();
	    assertNotNull(states);
	    assertFalse(states.isEmpty());
	} else {
	    fail("Service mismatch");
	}
    }

    @Test
    void testDistrict() throws Exception {
	if (supports()) {
	    Map<String, String> districts = ((DistrictCourtService) cs).districts("1");
	    assertNotNull(districts);
	    assertFalse(districts.isEmpty());
	} else {
	    fail("Service mismatch");
	}
    }

    @Test
    void testCourtComplexes() throws Exception {
	if (supports()) {
	    Map<String, String> complexes = ((DistrictCourtService) cs).courtComplexes("1", "26");
	    assertNotNull(complexes);
	    assertFalse(complexes.isEmpty());
	} else {
	    fail("Service mismatch");
	}
    }

    @Test
    void testCourtEstablishmentes() throws Exception {
	if (supports()) {
	    Map<String, String> establishments = ((DistrictCourtService) cs).courtEstablishments("1", "26");
	    assertNotNull(establishments);
	    assertFalse(establishments.isEmpty());
	} else {
	    fail("Service mismatch");
	}
    }

    private boolean supports() {
	return cs.supports(CourtService.class, Courts.ECOURT_DISTRICT_COURT.code());
    }
}

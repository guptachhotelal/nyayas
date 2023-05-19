package com.nyayas.status;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nyayas.common.constant.APIConstants;
import com.nyayas.common.service.ServiceFactory;
import com.nyayas.status.service.CaseNumberService;

import jakarta.annotation.Resource;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = CaseStatusByCaseNumberApplication.class)
@AutoConfigureMockMvc
public class CaseStatusByCaseNumberTest {

	@Resource
	protected ServiceFactory<CaseNumberService> caseNumberFactory;

	@LocalServerPort
	private int port;

	protected ObjectMapper mapper = new ObjectMapper();

	@Resource
	protected MockMvc mockMvc;
	
	protected CaseNumberService cns;

	@Test
	public void contextLoads() {
	}

	@Test
	void testMockMvcNull() {
		assertNotNull(mockMvc);
	}

	protected String uri() {
		return "http://localhost:" + port + "/" + APIConstants.CASE_STATUS_BASE + "/case-number";
	}
}

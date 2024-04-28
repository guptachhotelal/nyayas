package com.nyayas;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.servlet.MockMvc;

import com.nyayas.common.service.ServiceFactory;
import com.nyayas.service.CourtService;

import jakarta.annotation.Resource;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = CourtServiceApplication.class)
@AutoConfigureMockMvc
public class CourtServiceApplicationTest {

    @Resource
    protected ServiceFactory<CourtService> serviceFactory;

    protected CourtService cs = null;

    @LocalServerPort
    private int port;

    @Resource
    protected MockMvc mockMvc;

    @Test
    public void contextLoads() {
    }

    @Test
    void testMockMvcNull() {
	assertNotNull(mockMvc);
    }

    protected String uri() {
	return "http://localhost:" + port + "/v1/courts";
    }
}

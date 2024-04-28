package com.nyayas.captcha;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nyayas.captcha.service.CaptchaService;
import com.nyayas.common.service.ServiceFactory;

import jakarta.annotation.Resource;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = CaptchaServiceApplication.class)
@AutoConfigureMockMvc
public class CaptchaServiceApplicationTest {

    @Resource
    protected ServiceFactory<CaptchaService> serviceFactory;

    protected ObjectMapper mapper = new ObjectMapper();

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
	return "http://localhost:" + port + "/v1/captcha";
    }
}

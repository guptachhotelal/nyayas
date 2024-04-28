package com.nyayas.captcha;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import com.nyayas.captcha.service.CaptchaService;
import com.nyayas.common.service.ServiceFactory;

import jakarta.annotation.Resource;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CaptchaServiceFactoryTest {

    @Resource
    private ServiceFactory<CaptchaService> factory;

    private String[] captchaServices = { "internal", "external", "text" };

    @Test
    @Order(1)
    void testGetCaptchaService() {
	assertThat(factory).isNotNull();
	for (String cs : captchaServices) {
	    assertThat(factory.getService(CaptchaService.class, cs)).isNotNull();
	}
    }

    @Test
    @Order(2)
    void testNonExistentService() {
	String name = "xyz";
	String message = "No service with id " + name + " exist.";
	assertThat(factory).isNotNull();
	Exception ex = assertThrows(IllegalArgumentException.class,
		() -> factory.getService(CaptchaService.class, name));
	assertEquals(message, ex.getMessage());
    }

    @Test
    @Order(3)
    void testNonInitializedService() {
	factory.setServices(null);
	String name = "abc";
	String message = "No Service initialized.";
	assertThat(factory).isNotNull();
	Exception ex = assertThrows(IllegalStateException.class, () -> factory.getService(CaptchaService.class, name));
	assertEquals(message, ex.getMessage());
    }

}

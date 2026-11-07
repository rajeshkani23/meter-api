package com.flockenergy.meter_api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
		"urja.portal.email=test@example.com",
		"urja.portal.password=test-password"
})
class MeterApiApplicationTests {

	@Test
	void contextLoads() {
	}
}
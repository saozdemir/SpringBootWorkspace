package com.sao.backendapi;

import com.sao.starter.BackendApiApplicationStarter;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = BackendApiApplicationStarter.class)
class BackendApiApplicationTests {

	@Test
	void contextLoads() {
	}

}

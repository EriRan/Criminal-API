package fi.eriran.criminalapi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(
        locations = "classpath:hikari.properties"
)
class ApplicationTests {

	@Test
	void contextLoads() {
		//Just check that the Spring is able to initialize
	}

}

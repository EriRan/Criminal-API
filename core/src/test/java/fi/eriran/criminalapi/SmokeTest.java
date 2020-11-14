package fi.eriran.criminalapi;

import fi.eriran.criminalapi.main.GraphQLProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(
        locations = "classpath:hikari.properties"
)
class SmokeTest {

	@Autowired
	private GraphQLProvider graphQLProvider;

	@Test
	void contextLoads() {
		assertNotNull(graphQLProvider);
	}

}

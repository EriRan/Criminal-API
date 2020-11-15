package fi.eriran.criminalapi;

import fi.eriran.criminalapi.main.dao.CriminalDao;
import fi.eriran.criminalapi.testutil.annotation.CriminalApiSpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@CriminalApiSpringBootTest
class SmokeTest {

	@Autowired
	private CriminalDao criminalDao;

	@Test
	void contextLoads() {
		assertNotNull(criminalDao);
	}

}

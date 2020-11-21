package fi.eriran.criminalapi;

import fi.eriran.criminalapi.main.dao.CriminalDao;
import fi.eriran.criminalapi.testcore.annotation.CriminalApiSpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

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

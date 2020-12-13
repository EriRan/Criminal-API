package fi.eriran.criminalapi.main.rest;

import fi.eriran.criminalapi.testcore.annotation.CriminalApiSpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;

@CriminalApiSpringBootTest
class IndexPageTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void indexPageShouldBeOk() {
        ResponseEntity<String> response = testRestTemplate
                .getForEntity(URI.create("/"), String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().contains("This is Criminal API"));
    }
}

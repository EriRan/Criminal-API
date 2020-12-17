package fi.eriran.criminalapi.main.rest;

import fi.eriran.criminalapi.testcore.annotation.CriminalApiSpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertEquals;

@CriminalApiSpringBootTest
class GreetingControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void getEndpointShouldReturnOk() {
        ResponseEntity<String> response = testRestTemplate
                .withBasicAuth("support", "guy")
                .getForEntity(URI.create("/api/v1/greeting"), String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
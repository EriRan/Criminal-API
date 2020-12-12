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
class LoginControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void loginShouldReturnOk() {
        ResponseEntity<String> response = testRestTemplate
                .withBasicAuth("spring", "spring")
                .postForEntity(URI.create("/login"), null, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
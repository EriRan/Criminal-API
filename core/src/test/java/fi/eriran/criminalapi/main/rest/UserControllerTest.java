package fi.eriran.criminalapi.main.rest;

import fi.eriran.criminalapi.main.pojo.user.NewUser;
import fi.eriran.criminalapi.main.pojo.user.User;
import fi.eriran.criminalapi.testcore.annotation.CriminalApiSpringBootTest;
import fi.eriran.criminalapi.testcore.testdata.NewUserTestObjectFactory;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;

@CriminalApiSpringBootTest
class UserControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void passwordCanBeChanged() {
        NewUser newUser = new NewUserTestObjectFactory().create(RandomStringUtils.random(10));
        User createdUser = createNewUser(newUser);
        User patchedUser = testRestTemplate
                .withBasicAuth("support", "guy")
                .patchForObject(URI.create("/api/v1/user/" + createdUser.getId()), "newPassword", User.class);
        assertEquals(createdUser.getId(), patchedUser.getId());

        //Todo: actually use the new password in some way
    }

    private User createNewUser(NewUser newUser) {
        ResponseEntity<User> response = testRestTemplate
                .withBasicAuth("support", "guy")
                .postForEntity(URI.create("/api/v1/user"), newUser, User.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        User createdUser = response.getBody();
        assertNotNull(createdUser);
        assertNotNull(createdUser.getId());
        assertNotNull(createdUser.getUsername());
        return createdUser;
    }
}
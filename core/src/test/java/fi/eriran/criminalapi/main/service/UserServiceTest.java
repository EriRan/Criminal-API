package fi.eriran.criminalapi.main.service;

import fi.eriran.criminalapi.main.dao.user.UserDao;
import fi.eriran.criminalapi.main.pojo.Criminal;
import fi.eriran.criminalapi.main.pojo.user.NewUser;
import fi.eriran.criminalapi.main.pojo.user.User;
import fi.eriran.criminalapi.testcore.annotation.CriminalApiSpringBootTest;
import fi.eriran.criminalapi.testcore.testdata.NewUserTestObjectFactory;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static graphql.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@CriminalApiSpringBootTest
class UserServiceTest {

    @MockBean
    private UserDao userDao;
    @Autowired
    private UserService userService;

    private final NewUserTestObjectFactory testObjectFactory;

    public UserServiceTest() {
        testObjectFactory = new NewUserTestObjectFactory();
    }

    @Test
    @Disabled("Disabled until password hashing is added")
    void passwordHashed() {
        when(userDao.insert(any())).thenReturn(new User());
        NewUser newUser = testObjectFactory.create();
        String passwordBefore = newUser.getPassword();
        userService.create(newUser);
        assertNotNull(newUser.getPassword());
        assertNotEquals(newUser.getPassword(), passwordBefore);
    }
}
package fi.eriran.criminalapi.testcore.testdata;

import fi.eriran.criminalapi.main.pojo.user.NewUser;

public class NewUserTestObjectFactory {

    public NewUser create() {
        NewUser newUser = new NewUser();
        newUser.setUserName("Test");
        newUser.setPassword("moi123");
        return newUser;
    }
}

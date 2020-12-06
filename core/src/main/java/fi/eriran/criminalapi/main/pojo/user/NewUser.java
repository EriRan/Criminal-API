package fi.eriran.criminalapi.main.pojo.user;

/**
 * User object that contains the variables needed to create a new user
 */
public class NewUser {

    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

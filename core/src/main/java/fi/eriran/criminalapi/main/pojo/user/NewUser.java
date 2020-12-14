package fi.eriran.criminalapi.main.pojo.user;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * User object that contains the variables needed to create a new user
 */
@Table(name = "user")
public class NewUser {

    @Column(name = "username")
    private String userName;
    @Column(name = "password")
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

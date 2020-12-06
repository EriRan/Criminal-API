package fi.eriran.criminalapi.main.pojo.user;

import javax.persistence.Column;

public class User {

    @Column(name = "id")
    private Integer id;
    @Column(name = "username")
    private String username;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

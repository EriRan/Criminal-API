package fi.eriran.criminalapi.main.pojo;

import javax.persistence.Column;

public class Charge {

    @Column(name = "id")
    private Integer id;
    @Column(name = "description")
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

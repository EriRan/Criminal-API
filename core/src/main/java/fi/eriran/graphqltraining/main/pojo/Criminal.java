package fi.eriran.graphqltraining.main.pojo;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.Collection;


public class Criminal {

    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "appearance")
    private String appearance;
    private Collection<Charge> charges;

    public Criminal() {
        charges = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAppearance() {
        return appearance;
    }

    public void setAppearance(String appearance) {
        this.appearance = appearance;
    }

    public Collection<Charge> getCharges() {
        return charges;
    }

    public void setCharges(Collection<Charge> charges) {
        this.charges = charges;
    }
}

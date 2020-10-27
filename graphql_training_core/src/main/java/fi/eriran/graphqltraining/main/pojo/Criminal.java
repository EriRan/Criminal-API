package fi.eriran.graphqltraining.main.pojo;

import java.util.ArrayList;
import java.util.Collection;

public class Criminal {

    private Integer id;
    private String name;
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

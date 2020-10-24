package fi.eriran.graphqltraining.main.pojo;

import java.util.Collection;

public class Criminal {

    private String name;
    private String description;
    private Collection<Charge> charges;

    public Criminal() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<Charge> getCharges() {
        return charges;
    }

    public void setCharges(Collection<Charge> charges) {
        this.charges = charges;
    }
}

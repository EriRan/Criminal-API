package fi.eriran.criminalapi.testcore.testdata;

import fi.eriran.criminalapi.main.pojo.Charge;
import fi.eriran.criminalapi.main.pojo.Criminal;

public class ChargeTestObjectFactory {

    private ChargeTestObjectFactory() {
    }

    public static Charge create(int id) {
        Charge charge = new Charge();
        charge.setId(id);
        charge.setDescription("Test");
        return charge;
    }
}

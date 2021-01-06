package fi.eriran.criminalapi.testcore.testdata;

import fi.eriran.criminalapi.main.pojo.Criminal;

public class CriminalTestObjectFactory {

    private CriminalTestObjectFactory() {
    }

    public static Criminal create(int id) {
        Criminal criminal = new Criminal();
        criminal.setId(id);
        criminal.setAppearance("Test");
        criminal.setName("Test");
        return criminal;
    }
}

package fi.eriran.criminalapi.testcore.testdata;

import fi.eriran.criminalapi.main.pojo.Charge;
import fi.eriran.criminalapi.main.pojo.Sighting;

import java.time.OffsetDateTime;

public class SightingTestObjectFactory {

    private SightingTestObjectFactory() {
    }

    public static Sighting create(int id) {
        Sighting sighting = new Sighting();
        sighting.setId(id);
        sighting.setDescription("Test");
        sighting.setAddress("Test");
        sighting.setArea("Test");
        sighting.setTimeOfSighting(OffsetDateTime.now());
        return sighting;
    }
}

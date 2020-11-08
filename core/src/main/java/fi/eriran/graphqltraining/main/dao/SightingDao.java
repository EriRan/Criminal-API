package fi.eriran.graphqltraining.main.dao;

import fi.eriran.generated.jooq.Tables;
import fi.eriran.graphqltraining.main.pojo.Sighting;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

public class SightingDao {

    @Autowired
    private DSLContext ctx;

    public Collection<Sighting> getSightings(Integer criminalId) {
        return ctx.selectFrom(Tables.SIGHTING)
                .where(Tables.SIGHTING.CRIMINAL_ID.eq(criminalId))
                .fetchInto(Sighting.class);
    }
}

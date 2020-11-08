package fi.eriran.graphqltraining.main.dao;

import fi.eriran.graphqltraining.main.pojo.Sighting;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

import static fi.eriran.generated.jooq.Tables.SIGHTING;

@Component
public class SightingDao {

    @Autowired
    private DSLContext ctx;

    public Collection<Sighting> getSightings(Integer criminalId) {
        return ctx.selectFrom(SIGHTING)
                .where(SIGHTING.CRIMINAL_ID.eq(criminalId))
                .fetchInto(Sighting.class);
    }
}

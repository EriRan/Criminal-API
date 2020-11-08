package fi.eriran.criminalapi.main.dao;

import fi.eriran.generated.jooq.Tables;
import fi.eriran.criminalapi.main.pojo.Charge;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class ChargeDao {

    @Autowired
    private DSLContext ctx;

    public Collection<Charge> getCharges(Integer criminalId) {
        return ctx.selectFrom(Tables.CHARGE).where(Tables.CHARGE.CRIMINAL_ID.eq(criminalId))
                .fetchInto(Charge.class);
    }
}

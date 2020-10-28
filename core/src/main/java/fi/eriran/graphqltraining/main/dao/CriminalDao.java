package fi.eriran.graphqltraining.main.dao;

import fi.eriran.generated.jooq.Tables;
import fi.eriran.graphqltraining.main.pojo.Criminal;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CriminalDao {

    @Autowired
    private DSLContext ctx;

    public Criminal getCriminal(Integer id) {
        return ctx.selectFrom(Tables.CRIMINAL_)
                .where(Tables.CRIMINAL_.ID.eq(id))
                .fetchOneInto(Criminal.class);
    }
}

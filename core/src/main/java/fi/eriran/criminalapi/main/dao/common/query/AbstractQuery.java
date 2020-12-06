package fi.eriran.criminalapi.main.dao.common.query;

import org.jooq.DSLContext;

public abstract class AbstractQuery {

    protected DSLContext ctx;

    protected AbstractQuery(DSLContext ctx) {
        this.ctx = ctx;
    }
}

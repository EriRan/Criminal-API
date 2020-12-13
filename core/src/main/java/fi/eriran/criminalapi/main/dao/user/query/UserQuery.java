package fi.eriran.criminalapi.main.dao.user.query;

import fi.eriran.criminalapi.main.dao.common.query.AbstractQuery;
import fi.eriran.criminalapi.main.pojo.user.NewUser;
import fi.eriran.generated.jooq.tables.records.UserRecord;
import org.jooq.*;
import org.springframework.stereotype.Component;

import static fi.eriran.generated.jooq.tables.User.USER;

@Component
public class UserQuery extends AbstractQuery {

    protected UserQuery(DSLContext ctx) {
        super(ctx);
    }

    public InsertResultStep<UserRecord> insert(NewUser newUser) {
        return ctx.insertInto(USER)
                .set(USER.USERNAME, newUser.getUserName())
                .set(USER.PASSWORD, newUser.getPassword())
                .returning();
    }

    public SelectConditionStep<UserRecord> select(Integer userId) {
        return ctx.selectFrom(USER).where(USER.ID.eq(userId));
    }

    public UpdateConditionStep<UserRecord> updatePassword(Integer userId, String newPassword) {
        return ctx.update(USER).set(USER.PASSWORD, newPassword).where(USER.ID.eq(userId));
    }
}

package fi.eriran.criminalapi.main.dao.user.query;

import fi.eriran.criminalapi.main.dao.common.query.AbstractQuery;
import fi.eriran.criminalapi.main.pojo.user.NewUser;
import fi.eriran.generated.jooq.tables.records.UserRecord;
import org.jooq.DSLContext;
import org.jooq.InsertQuery;
import org.springframework.stereotype.Component;

import static fi.eriran.generated.jooq.tables.User.USER;

@Component
public class UserQuery extends AbstractQuery {

    protected UserQuery(DSLContext ctx) {
        super(ctx);
    }

    public InsertQuery<UserRecord> insert(NewUser newUser) {
        InsertQuery<UserRecord> insertQuery = ctx.insertQuery(USER);
        insertQuery.addRecord(createInsertRecord(newUser));
        insertQuery.setReturning();
        return insertQuery;
    }

    private UserRecord createInsertRecord(NewUser newUser) {
        UserRecord userRecord = new UserRecord();
        userRecord.setUsername(newUser.getUserName());
        userRecord.setPassword(newUser.getPassword());
        return userRecord;
    }
}

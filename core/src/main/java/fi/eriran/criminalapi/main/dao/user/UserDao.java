package fi.eriran.criminalapi.main.dao.user;

import fi.eriran.criminalapi.main.dao.user.query.UserQuery;
import fi.eriran.criminalapi.main.pojo.user.NewUser;
import fi.eriran.criminalapi.main.pojo.user.User;
import fi.eriran.generated.jooq.tables.records.UserRecord;
import org.jooq.InsertQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDao {

    @Autowired
    private UserQuery userQuery;

    public User insert(NewUser newUser) {
        InsertQuery<UserRecord> insertQuery = userQuery.insert(newUser);
        insertQuery.execute();
        return insertQuery.getReturnedRecord().into(User.class);
    }
}

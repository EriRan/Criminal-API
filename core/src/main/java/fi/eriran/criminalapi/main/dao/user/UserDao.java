package fi.eriran.criminalapi.main.dao.user;

import fi.eriran.criminalapi.main.dao.user.query.UserQuery;
import fi.eriran.criminalapi.main.pojo.user.NewUser;
import fi.eriran.criminalapi.main.pojo.user.User;
import fi.eriran.generated.jooq.tables.records.UserRecord;
import org.jooq.InsertResultStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDao {

    @Autowired
    private UserQuery userQuery;

    public User insert(NewUser newUser) {
        InsertResultStep<UserRecord> insertQuery = userQuery.insert(newUser);
        return insertQuery.fetchOne().into(User.class);
    }

    public User select(Integer userId) {
        return userQuery.select(userId).fetchOneInto(User.class);
    }

    public boolean updatePassword(Integer userId, String newPassword) {
        return userQuery.updatePassword(userId, newPassword).execute() == 1;
    }
}

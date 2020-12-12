package fi.eriran.criminalapi.main.service;

import fi.eriran.criminalapi.main.dao.user.UserDao;
import fi.eriran.criminalapi.main.pojo.user.NewUser;
import fi.eriran.criminalapi.main.pojo.user.User;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    @Autowired
    private UserDao userDao;

    public User create(NewUser newUser) {
        //Hash the password
        if (!ObjectUtils.allNotNull(newUser.getUserName(), newUser.getPassword())) {
            return null;
        }
        //todo: Hash the password
        newUser.setPassword(newUser.getPassword());
        return userDao.insert(newUser);
    }
}

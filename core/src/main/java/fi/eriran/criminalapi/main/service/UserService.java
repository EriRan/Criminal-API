package fi.eriran.criminalapi.main.service;

import fi.eriran.criminalapi.main.dao.user.UserDao;
import fi.eriran.criminalapi.main.exception.CriminalApiException;
import fi.eriran.criminalapi.main.pojo.user.ChangePassword;
import fi.eriran.criminalapi.main.pojo.user.NewUser;
import fi.eriran.criminalapi.main.pojo.user.User;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User create(NewUser newUser) {
        //Hash the password
        if (!ObjectUtils.allNotNull(newUser.getUserName(), newUser.getPassword())) {
            return null;
        }
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        return userDao.insert(newUser);
    }

    public User getUser(Integer userId) {
        return userDao.select(userId);
    }

    public User changePassword(ChangePassword changePassword) {
        User user = userDao.select(changePassword.getUserId());
        if (user == null) {
            return user;
        }
        boolean updateSuccessful = userDao.updatePassword(
                user.getId(),
                passwordEncoder.encode(changePassword.getNewPassword())
        );
        if (!updateSuccessful) {
            throw new CriminalApiException("Unable to update the password");
        }
        return user;
    }
}

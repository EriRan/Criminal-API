package fi.eriran.criminalapi.main.rest;

import fi.eriran.criminalapi.main.pojo.user.NewUser;
import fi.eriran.criminalapi.main.pojo.user.User;
import fi.eriran.criminalapi.main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public User create(@RequestBody NewUser newUser) {
        return userService.create(newUser);
    }
}

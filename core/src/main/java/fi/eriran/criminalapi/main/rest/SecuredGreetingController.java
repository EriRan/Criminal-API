package fi.eriran.criminalapi.main.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greeting/secured")
public class SecuredGreetingController {

    @GetMapping
    public String get() {
        return "Hey secret!";
    }
}
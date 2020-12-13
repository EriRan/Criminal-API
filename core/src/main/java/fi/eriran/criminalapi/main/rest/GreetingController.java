package fi.eriran.criminalapi.main.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greeting")
public class GreetingController {

    @GetMapping
    public String get() {
        return "Hey get!";
    }

    @PostMapping
    public String post() {
        return "Hello Post!";
    }
}
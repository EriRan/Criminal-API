package fi.eriran.criminalapi.main.rest;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/greeting")
public class GreetingController {

    @GetMapping
    public String get() {
        return "Hey get!";
    }
}

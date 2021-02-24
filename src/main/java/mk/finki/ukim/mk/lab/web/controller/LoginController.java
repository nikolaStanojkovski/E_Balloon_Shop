package mk.finki.ukim.mk.lab.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/login")
public class LoginController {

    @GetMapping
    public String getLoginPage() {
        return "login.html";
    }

}

package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.enums.Role;
import mk.finki.ukim.mk.lab.model.exceptions.InvalidArgumentsException;
import mk.finki.ukim.mk.lab.model.exceptions.PasswordsDontMatchException;
import mk.finki.ukim.mk.lab.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = "/register")
public class RegisterController {

    private UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getRegisterPage(@RequestParam(required = false) String error,
                                  Model model) {
        if(error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        model.addAttribute("roles", Role.values());
        return "register";
    }

    @PostMapping
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String repeatPassword,
                           @RequestParam Role role) {
        try {
            this.userService.registerUser(username, password, repeatPassword, role);
            return "redirect:/login";
        } catch (InvalidArgumentsException | PasswordsDontMatchException exception) {
            return "redirect:/register?error=" + exception.getMessage();
        }
    }

}

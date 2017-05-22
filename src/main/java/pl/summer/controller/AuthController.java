package pl.summer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.summer.model.dto.UserRegistrationDto;
import pl.summer.model.service.UserService;

/**
 * Created by howor on 15.04.2017.
 */

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model) {
        UserRegistrationDto newUser = new UserRegistrationDto();

        model.addAttribute("newUser", newUser);
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerFormFiller(@ModelAttribute("newUser") UserRegistrationDto newUser) {
        userService.registerNewUser(newUser);
        return "user_registered";
    }

}

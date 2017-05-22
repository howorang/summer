package pl.summer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.summer.model.entity.UserEntity;
import pl.summer.model.service.UserService;

/**
 * Created by Piotr Borczyk on 21.05.2017.
 */

@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPanel(@RequestParam(name = "page", required = false, defaultValue = "0") Integer pageNumber,
                             Model model) {
        Page<UserEntity> usersPage = userService.getUsers(pageNumber);
        model.addAttribute("page", usersPage);
        model.addAttribute("users", usersPage.getContent());
        return "admin";
    }
}

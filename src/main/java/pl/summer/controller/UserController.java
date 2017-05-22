package pl.summer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pl.summer.model.entity.UserEntity;
import pl.summer.model.service.UserService;

import java.io.IOException;
import java.util.Base64;

/**
 * Created by Piotr Borczyk on 21.05.2017.
 */

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/{userName}", method = RequestMethod.GET)
    public String viewProfile(@PathVariable String userName,
                              Model model) {
        UserEntity user = userService.getUserByUsername(userName);
        model.addAttribute("user", user);
        model.addAttribute("encodedAvatar", Base64.getEncoder().encodeToString(user.getUserInfo().getAvatar()));
        return "user/profile";
    }

    @RequestMapping(value = "/{userName}/fileUpload", method = RequestMethod.POST)
    public String uploadAvatar(@PathVariable String userName,
                               @RequestParam("file") MultipartFile file,
                               Model model) {
        try {
            byte[] data = file.getBytes();
            UserEntity user = userService.getUserByUsername(userName);
            user.getUserInfo().setAvatar(data);
            userService.save(user);
            model.addAttribute("user", user);
            model.addAttribute("encodedAvatar", Base64.getEncoder().encodeToString(user.getUserInfo().getAvatar()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "user/profile";
    }
}

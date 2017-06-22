package pl.summer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pl.summer.model.dto.DescriptionDto;
import pl.summer.model.dto.SearchDto;
import pl.summer.model.entity.UserEntity;
import pl.summer.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
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

    @ModelAttribute(name = "newDescription")
    public SearchDto searchDto(){
        SearchDto searchDto = new SearchDto();
        searchDto.setQueryString("");
        return searchDto;
    }

    @RequestMapping(value = "/{userName}", method = RequestMethod.GET)
    public String viewProfile(@PathVariable String userName,
                              Model model) {
        UserEntity user = userService.getUserByUsername(userName);
        model.addAttribute("user", user);
        model.addAttribute("encodedAvatar", user.getUserInfo().getAvatar());
        return "user/profile";
    }

    @RequestMapping(value = "/{userName}/fileUpload", method = RequestMethod.POST)
    public String uploadAvatar(@PathVariable String userName,
                               @RequestParam("file") MultipartFile file,
                               Model model) {
        try {
            byte[] data = file.getBytes();
            UserEntity user = userService.getUserByUsername(userName);
            user.getUserInfo().setAvatar(Base64.getEncoder().encodeToString(data));
            userService.save(user);
            model.addAttribute("user", user);
            model.addAttribute("encodedAvatar", user.getUserInfo().getAvatar());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "user/profile";
    }

    @RequestMapping(value = "/{userName}/editDescription", method = RequestMethod.GET)
    public String editDescription(@PathVariable String userName) {
        return "fragments/edit_description_form :: edit_description_form(username=" + userName + ")";
    }

    @RequestMapping(value = "/{userName}/editDescription", method = RequestMethod.POST)
    public String editDescriptionPost(@PathVariable String userName,
                                      @RequestParam String newDescription) {
        UserEntity user = userService.getUserByUsername(userName);
        user.getUserInfo().setDescription(newDescription);
        userService.save(user);
        return "redirect: ";
    }
}

package pl.summer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Piotr Borczyk on 10.04.2017.
 */

@Controller
public class SecuredController {

    @RequestMapping(path = "/secured", method = RequestMethod.GET)
    public String home() {
        return "secured";
    }

}

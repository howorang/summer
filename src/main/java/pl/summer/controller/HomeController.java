package pl.summer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.summer.model.entity.EntryEntity;
import pl.summer.model.service.EntryService;

import java.util.List;

/**
 * Created by Piotr Borczyk on 10.04.2017.
 */

@Controller
public class HomeController {

    @Autowired
    private EntryService entryService;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model) {
        List<EntryEntity> entities = entryService.getAllEntries();
        model.addAttribute(entities);
        return "home";
    }
}

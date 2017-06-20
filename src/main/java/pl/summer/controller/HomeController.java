package pl.summer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    public String home(Model model, @RequestParam(name = "page", required = false) Integer pageNumber) {
        if (pageNumber == null) {
            pageNumber = 0;
        }

        Page<EntryEntity> entries = entryService.getEntries(pageNumber);
        model.addAttribute("entries", entries.getContent());
        model.addAttribute("page", entries);
        return "home";
    }
}
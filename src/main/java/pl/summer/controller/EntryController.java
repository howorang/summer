package pl.summer.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.summer.model.dto.EntryDto;
import pl.summer.model.service.EntryService;

/**
 * Created by Piotr Borczyk on 25.04.2017.
 */
@Controller
@RequestMapping("/entry")
public class EntryController {

    @Autowired
    private EntryService entryService;

    @RequestMapping(path = "/add", method = RequestMethod.GET)
    public String addEntryDisplay(Model model) {
        EntryDto entry = new EntryDto();
        model.addAttribute("entry", entry);
        return "entry/add";
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public String addEntryPost(@ModelAttribute(name = "entry") EntryDto entry) {
        entryService.addEntry(entry);
        return "entry/add";
    }
}

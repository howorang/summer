package pl.summer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.summer.model.entity.EntryEntity;
import pl.summer.model.service.EntryService;

import java.util.List;

/**
 * Created by howor on 20.05.2017.
 */

@Controller
@RequestMapping("/tag")
public class HashTagController {

    @Autowired
    private EntryService entryService;

    @RequestMapping(value = "/{tagName}", method = RequestMethod.GET)
    public String browseHashTag(@PathVariable String tagName,
                                @RequestParam(name = "page", required = false, defaultValue = "0") Integer pageNumber,
                                Model model) {
        Page<EntryEntity> page = entryService.getEntriesWithHashTag(tagName, pageNumber);
        model.addAttribute("page", page);
        model.addAttribute("entries", page.getContent());
        return "home";
    }
}

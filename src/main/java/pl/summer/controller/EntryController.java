package pl.summer.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.summer.model.dto.EntryDto;
import pl.summer.model.dto.SearchDto;
import pl.summer.model.entity.EntryEntity;
import pl.summer.model.service.EntryService;

import java.util.Collections;
import java.util.List;

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
        return "redirect:/";
    }

    @RequestMapping(path = "/remove", method = RequestMethod.GET)
    public String removeEntry(@RequestParam(name = "entry_id") Integer entryId) {
        entryService.removeEntry(entryId);
        return "entry_removed";
    }

    @ModelAttribute(name = "search_bean")
    public SearchDto searchDto(){
        SearchDto searchDto = new SearchDto();
        searchDto.setQueryString("");
        return searchDto;
    }

    @RequestMapping(path = "/search", method = RequestMethod.GET)
    public String searchEntries(@RequestParam(defaultValue = "") String query,
                                @RequestParam(name = "page" ,defaultValue = "0") Integer pageNumber,
                                Model model) {
        Page<EntryEntity> entries = entryService.getAllEntriesContaining(query, pageNumber);
        model.addAttribute("page", entries);
        model.addAttribute("entries", entries.getContent());
        model.addAttribute("current_query", query);
        return "entry/search";
    }


    @RequestMapping(path = "/search", method = RequestMethod.POST)
    public String searchEntries(@ModelAttribute(name = "search_bean") SearchDto searchDto,
                                Model model) {
        String query = searchDto.getQueryString();
        return "redirect:/entry/search?query=" + query;
    }

    @RequestMapping(path = "/details", method = RequestMethod.GET)
    public String viewEntry(@RequestParam(name = "entryId") Long entryId,
                            Model model) {
        EntryEntity entry = entryService.getEntryById(entryId);
        model.addAttribute("entries", Collections.singletonList(entry));
        return "home";
    }

    @RequestMapping(path = "/plus", method = RequestMethod.GET,  produces = MediaType.TEXT_HTML_VALUE)
    public String plus(@RequestParam(name = "entryId") Long entryId) {
        int upvotes = entryService.upvoteEntry(entryId);
        return "fragments/utils :: toString(object=" + upvotes +")";
    }

    @RequestMapping(path = "/hot", method = RequestMethod.GET)
    public String home(Model model, @RequestParam(name = "page", required = false) Integer pageNumber) {
        if (pageNumber == null) {
            pageNumber = 0;
        }

        Page<EntryEntity> entries = entryService.getHotEntries(pageNumber);
        model.addAttribute("entries", entries.getContent());
        model.addAttribute("page", entries);
        return "home";
    }

}

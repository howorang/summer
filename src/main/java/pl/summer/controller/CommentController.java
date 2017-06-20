package pl.summer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.summer.model.entity.CommentEntity;
import pl.summer.model.entity.UserEntity;
import pl.summer.model.service.CommentService;
import pl.summer.model.service.UserService;

/**
 * Created by Piotr Borczyk on 20.06.2017.
 */

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping(path = "/comment/load_panel", method = RequestMethod.GET)
    public String loadAddCommentPanel(@RequestParam(name = "entryId") Long entryId,
                                      Model model) {
        model.addAttribute("newComment", new CommentEntity());
        model.addAttribute("entryId", entryId);
        return "fragments/add_comment_panel :: add_comment_panel";
    }

    @RequestMapping(path = "/comment/add", method = RequestMethod.POST)
    public String addComment(@RequestParam(name = "entryId") Long entryId,
                             @ModelAttribute(name = "newComment") CommentEntity newComment) {
        commentService.addComment(newComment, entryId);
        return "redirect:/entry/details?entryId=" + entryId;
    }


}

package pl.summer.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.summer.model.entity.CommentEntity;
import pl.summer.model.entity.EntryEntity;
import pl.summer.model.entity.UserEntity;
import pl.summer.model.repository.CommentRepository;
import pl.summer.model.repository.EntryRepository;

import javax.transaction.Transactional;

/**
 * Created by Piotr Borczyk on 20.06.2017.
 */

@Service
@Transactional
public class CommentService {

    @Autowired
    private EntryRepository entryRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserService userService;

    public void addComment(CommentEntity comment, long entryId) {
        UserEntity currentUser = userService.getCurrentlyLoggedUser();
        comment.setAuthor(currentUser);

        EntryEntity entry = entryRepository.findById(entryId);
        entry.getComments().add(comment);
        comment.setEntry(entry);
        commentRepository.save(comment);
    }
}

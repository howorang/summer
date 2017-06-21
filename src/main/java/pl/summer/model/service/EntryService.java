package pl.summer.model.service;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.summer.model.dto.EntryDto;
import pl.summer.model.entity.EntryEntity;
import pl.summer.model.entity.QEntryEntity;
import pl.summer.model.entity.UserEntity;
import pl.summer.model.entry.HashTagParser;
import pl.summer.model.entry.HashTagTransformer;
import pl.summer.model.entry.UserReferenceTransformer;
import pl.summer.model.repository.EntryRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import pl.summer.model.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by howor on 14.04.2017.
 */

@Service
@Transactional
public class EntryService {

    private static final int DEFAULT_PAGE_SIZE = 1;
    @Autowired
    private EntryRepository entryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    public void addEntry(EntryDto entryDto) {
        Set<String> hashTags = HashTagParser.getHashTags(entryDto.getContent());

        HashTagTransformer hashTagTransformer = new HashTagTransformer();
        entryDto = hashTagTransformer.transform(entryDto);

        UserReferenceTransformer userReferenceTransformer = new UserReferenceTransformer();
        entryDto = userReferenceTransformer.transform(entryDto);

        EntryEntity entry = EntryEntity.builder()
                .author(userService.getCurrentlyLoggedUser())
                .content(entryDto.getContent())
                .hashTags(hashTags)
                .upvotes(0)
                .timeStamp(new Date())
                .build();
        entryRepository.save(entry);
    }

    public Page<EntryEntity> getEntries(int pageNumber) {
        PageRequest pageRequest = new PageRequest(pageNumber, DEFAULT_PAGE_SIZE);
        return entryRepository.findAllByOrderByTimeStampDesc(pageRequest);
    }

    public Page<EntryEntity> getEntriesWithHashTag(String hashTag, int pageNumber) {
        PageRequest pageRequest = new PageRequest(pageNumber, DEFAULT_PAGE_SIZE);
        QEntryEntity qEntryEntity = QEntryEntity.entryEntity;
        BooleanExpression query = qEntryEntity.hashTags.contains(hashTag);
        return entryRepository.findAll(query, pageRequest);
    }

    public void removeEntry(long entryId) {
        entryRepository.delete(entryId);
    }

    public Page<EntryEntity> getAllEntriesContaining(String query, int pageNumber) {
        QEntryEntity qEntryEntity = QEntryEntity.entryEntity;
        BooleanExpression queryExp = qEntryEntity.content.contains(query).or(qEntryEntity.author.username.contains(query));
        PageRequest pageRequest = new PageRequest(pageNumber, DEFAULT_PAGE_SIZE);
        return entryRepository.findAll(queryExp, pageRequest);
    }

    public EntryEntity getEntryById(long id) {
        return entryRepository.findById(id);
    }

    public int upvoteEntry(Long entryId) {
        UserEntity currentUser = userService.getCurrentlyLoggedUser();
        if (currentUser.getUpvotedEntries().contains(entryId)) {
            return entryRepository.findById(entryId).getUpvotes();
        }
        currentUser.getUpvotedEntries().add(entryId);
        EntryEntity entry = entryRepository.findById(entryId);
        int upvotes = entry.getUpvotes() + 1;
        entry.setUpvotes(upvotes);
        entryRepository.save(entry);
        userRepository.save(currentUser);
        return upvotes;
    }

    public Page<EntryEntity> getHotEntries(int pageNumber) {
        PageRequest pageRequest = new PageRequest(pageNumber, DEFAULT_PAGE_SIZE);
        QEntryEntity qEntryEntity = QEntryEntity.entryEntity;

        return entryRepository.findAllByOrderByUpvotesDesc(pageRequest);
    }


}

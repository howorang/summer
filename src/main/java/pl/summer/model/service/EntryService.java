package pl.summer.model.service;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.summer.model.dto.EntryDto;
import pl.summer.model.entity.EntryEntity;
import pl.summer.model.entity.QEntryEntity;
import pl.summer.model.entry.HashTagParser;
import pl.summer.model.repository.EntryRepository;
import com.querydsl.core.types.dsl.BooleanExpression;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by howor on 14.04.2017.
 */

@Service
public class EntryService {

    private static final int DEFAULT_PAGE_SIZE = 1;
    @Autowired
    private EntryRepository entryRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public void addEntry(EntryDto entryDto) {
        Set<String> hashTags = HashTagParser.getHashTags(entryDto.getContent());
        EntryEntity entry = EntryEntity.builder()
                .author(userService.getCurrentlyLoggedUser())
                .content(entryDto.getContent())
                .hashTags(hashTags)
                .upvotes(0)
                .timeStamp(new Date())
                .build();
        entryRepository.save(entry);
    }

    @Transactional
    public Page<EntryEntity> getEntries(int pageNumber) {
        PageRequest pageRequest = new PageRequest(pageNumber, DEFAULT_PAGE_SIZE);
        return entryRepository.findAll(pageRequest);
    }

    @Transactional
    public Page<EntryEntity> getEntriesWithHashTag(String hashTag, int pageNumber) {
        PageRequest pageRequest = new PageRequest(pageNumber, DEFAULT_PAGE_SIZE);
        QEntryEntity qEntryEntity = QEntryEntity.entryEntity;
        BooleanExpression query = qEntryEntity.hashTags.contains(hashTag);
        return entryRepository.findAll(query, pageRequest);
    }
}

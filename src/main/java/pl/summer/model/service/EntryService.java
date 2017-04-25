package pl.summer.model.service;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.summer.model.dto.EntryDto;
import pl.summer.model.entity.EntryEntity;
import pl.summer.model.entity.UserEntity;
import pl.summer.model.entry.HashTagParser;
import pl.summer.model.repository.EntryRepository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by howor on 14.04.2017.
 */

@Service
public class EntryService {

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
    public List<EntryEntity> getAllEntries() {
        return Lists.newArrayList(entryRepository.findAll());
    }
}

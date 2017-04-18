package pl.summer.model.service;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.summer.model.entity.EntryEntity;
import pl.summer.model.entity.UserEntity;
import pl.summer.model.entry.HashTagParser;
import pl.summer.model.repository.EntryRepository;

import java.util.List;
import java.util.Set;

/**
 * Created by howor on 14.04.2017.
 */

@Service
public class EntryService {

    @Autowired
    private EntryRepository entryRepository;

    public void addEntry(UserEntity author, String content) {
        Set<String> hashTags = HashTagParser.getHashTags(content);
        EntryEntity newEntry = new EntryEntity(content, author, hashTags, 0);
        entryRepository.save(newEntry);
    }

    public List<EntryEntity> getAllEntries() {
        return Lists.newArrayList(entryRepository.findAll());
    }
}

package pl.summer.model.entry;

import pl.summer.model.dto.EntryDto;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Piotr Borczyk on 22.06.2017.
 */
public class HashTagTransformer implements EntryTransformer {

    private static final Pattern hashTagPattern = Pattern.compile("#([a-z]*)");
    private static final String replacmentTemplate = "<a href=\"/tag/{tagName}\">{tagName}</a>";

    @Override
    public EntryDto transform(EntryDto entryDto) {
        String content = entryDto.getContent();

        Matcher matcher = hashTagPattern.matcher(content);

        while (matcher.find()) {
            String hashTag = matcher.group(1);
            String replacement = replacmentTemplate.replace("{tagName}", hashTag);
            String newContent = matcher.replaceFirst(replacement);
            entryDto.setContent(newContent);
            matcher = hashTagPattern.matcher(entryDto.getContent());
        }
        return entryDto;
    }

}

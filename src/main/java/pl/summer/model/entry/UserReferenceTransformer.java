package pl.summer.model.entry;

import pl.summer.model.dto.EntryDto;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Piotr Borczyk on 22.06.2017.
 */
public class UserReferenceTransformer implements EntryTransformer {

    private static final Pattern userPattern = Pattern.compile("@([a-z]*)");
    private static final String replacmentTemplate = "<a href=\"/user/{username}\">{username}</a>";

    @Override
    public EntryDto transform(EntryDto entryDto) {
        String content = entryDto.getContent();

        Matcher matcher = userPattern.matcher(content);

        while (matcher.find()) {
            String hashTag = matcher.group(1);
            String replacement = replacmentTemplate.replace("{username}", hashTag);
            String newContent = matcher.replaceFirst(replacement);
            entryDto.setContent(newContent);
            matcher = userPattern.matcher(entryDto.getContent());
        }
        return entryDto;
    }

}

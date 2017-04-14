package pl.summer.model.entry;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by howor on 14.04.2017.
 */
public class HashTagParser {

    private static final Pattern hashTagPattern = Pattern.compile("#([a-z]*)");

    public static Set<String> getHashTags(String content) {
        Matcher matcher = hashTagPattern.matcher(content);
        Set<String> hashTags = new HashSet<>();

        while (matcher.find()) {
            hashTags.add(matcher.group(1));
        }

        return hashTags;
    }
}

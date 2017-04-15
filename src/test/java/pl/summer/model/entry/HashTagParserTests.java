package pl.summer.model.entry;

import static org.junit.Assert.*;

import org.assertj.core.util.Sets;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runners.Parameterized.Parameter;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

/**
 * Created by howor on 14.04.2017.
 */

@RunWith(Parameterized.class)
public class HashTagParserTests {

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { "to jest testowy #hashtag we wpisie a to #drugi", Sets.newLinkedHashSet("hashtag", "drugi") },
                { "To ma sens ( ͡° ͜ʖ ͡°) #heheszki #lenistwo", Sets.newLinkedHashSet("heheszki", "lenistwo")}
        });
    }

    @Parameter
    public String givenInput;

    @Parameter(1)
    public Set<String> expectedHashTags;

    @Test
    public void testHashTagParser() {
        Set<String> result = HashTagParser.getHashTags(givenInput);
        assertEquals(expectedHashTags, result);
    }
}

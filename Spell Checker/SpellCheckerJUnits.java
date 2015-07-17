import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;
import org.junit.Before;

public class SpellCheckerJUnits {

    private SpellChecker sc;
    
    @Before
    public void setup() {
        sc = new SpellChecker();
    }
    
    @Test
    public void minimumEditDistanceTest() {
        String s1 = "";
        String s2 = "sss";
        String s3 = "ssss";
        String s4 = "ass"; // heh

        String name1 = "francisco";
        String name2 = "nicole";
        
        assertEquals(3, sc.minimumEditDistance(s1, s2));
        assertEquals(4, sc.minimumEditDistance(s1, s3));
        assertEquals(1, sc.minimumEditDistance(s2, s3));
        assertEquals(1, sc.minimumEditDistance(s2, s4));
        assertEquals(2, sc.minimumEditDistance(s3, s4));
    }

    @Test
    public void spellCheckTest1() throws IOException {
        assertEquals(sc.spellCheck("heydude"), "deduce");
    }
}

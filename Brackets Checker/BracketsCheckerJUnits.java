import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class BracketsCheckerJUnits {
    
    private BracketsChecker bc;
    
    @Before
    public void setup() {
        bc = new BracketsChecker();
    }
    
    @Test
    public void blankTest() {
        String s = "";
        assertTrue(bc.finder(s));
    }

    @Test
    public void simpleValidTest() {
        String s = "()";
        assertTrue(bc.finder(s));        
    }
    
    @Test
    public void validWithTextTest() {
        String s0 = "[why?(hello)there]";
        String s1 = "[are{~~youstill}there_()()]";
        assertTrue(bc.finder(s0));
        assertTrue(bc.finder(s1));
    }
    
    @Test
    public void invalidWithTextTest() {
        String s0 = "[why(hello.)!there]]";
        String s1 = "[are{youstill(}_there()()]";
        assertFalse(bc.finder(s0));
        assertFalse(bc.finder(s1));
    }
    
    @Test
    public void simpleInvalidTest() {
        String s = "{(})";
        assertFalse(bc.finder(s));
    }
    
    @Test
    public void longValidTest() {
        String s = "(((({[{}]}))))";
        assertTrue(bc.finder(s));
    }
    
    @Test
    public void tooManyOpenBracketsTest() {
        String s = "([]";
        assertFalse(bc.finder(s));
    }
    
    @Test
    public void tooManyCloseBracketsTest() {
        String s = "()]";
        assertFalse(bc.finder(s));
    }
}

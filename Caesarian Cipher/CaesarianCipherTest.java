import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CaesarianCipherTest {

    @Test
    public void testSimpleEncipher() {
        String test = "F";
        String expected = "G";
        assertEquals(CaesarianCipher.encipher(test, 1), expected);
    }
    
    @Test
    public void testSimpleDecipher() {
        String test = "G";
        String expected = "F";
        assertEquals(CaesarianCipher.decipher(test, 1), expected);
    }
    
    @Test
    public void testComplexEncipher() {
        String test = "Hello, World!";
        String expected = "Ifmmp, Xpsme!";
        assertEquals(CaesarianCipher.encipher(test, 1), expected);
    }
    
    @Test
    public void testComplexDecipher() {
        String test = "Hello, World!";
        String expected = "Ifmmp, Xpsme!";
        assertEquals(CaesarianCipher.decipher(test, 25), expected);
    }

    @Test
    public void testEncipherLoop() {
        String test = "Hello, World!";
        assertEquals(CaesarianCipher.encipher(test,  26), test);
    }
    
    @Test
    public void testDecipherLoop() {
        String test = "Hello, World!";
        assertEquals(CaesarianCipher.decipher(test,  26), test);
    }
    
}

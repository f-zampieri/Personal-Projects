/**
 * This class will encapsulate methods for enciphering and deciphering strings
 * @author Francisco Zampieri
 * @version 1.0 
 */
public class CaesarianCipher {

    public static final String LOWERCASE_ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    public static final String UPPERCASE_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    
    public static String encipher(String input, int dist) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < input.length(); i++) {
            Character cur = input.charAt(i);
            // if given distance is negative or greater than 25, make it 0 <= dist < 25
            if (dist < 0) {
                dist = dist % 26 + 26;
            } else if  (dist >= 26) {
                dist = dist % 26;
            }
            // do shift
            if (LOWERCASE_ALPHABET.contains(cur.toString())) {
                cur = (char) ('a' + (cur - 'a' + dist) % 26);
            } else if (UPPERCASE_ALPHABET.contains(cur.toString())) {
                cur = (char) ('A' + (cur - 'A' + dist) % 26);
            }
            // add shifted char to StringBuffer
            sb.append(cur.toString());
        }
        return sb.toString();
    }
    
    // due to the wonders of modular arithmetic, deciphering is the same as
    // enciphering with a negative distance
    public static String decipher(String input, int dist) {
        return encipher(input, dist * -1);
    }
    
}

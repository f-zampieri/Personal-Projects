import java.util.ArrayList;
import java.util.PriorityQueue;
import java.io.*;

public class SpellChecker {
    
    public final int ARRAY_SIZE = 10;
    public final int MAX_MED = 6;
    
    /**
     * This method handles correcting the given string.
     * @param input The string to be corrected
     * @return      The correction to the string
     */
    public String spellCheck(String input) throws IOException {
        /* Priority Queue used to store the string with the minimum Levenshtein
         * Distance to the input string 
         */
        ArrayList<MEDValue> arr = new ArrayList<>(ARRAY_SIZE);
        File dict = new File("C:\\Users\\Pancho\\workspace\\Projects\\src\\dictionary.txt");
        String line;
        BufferedReader br = new BufferedReader(new FileReader(dict));
        MEDValue least = null;
        // Gets to the dictionary bit, past the copyrights
        while (!(line = br.readLine()).equals("---"));
        while ((line = br.readLine()) != null) {
            /* Minimum edit distance between the given string and the current
             * string in the array
             */
            int med = minimumEditDistance(input, line);
            // If the string is in the dictionary, end the program
            if (med == 0) {
                br.close();
                return line;
            }
            if (med < MAX_MED) {
                MEDValue toAdd = new MEDValue(med, line);
                if (null == least || toAdd.compareTo(least) < 0) {
                    least = toAdd;
                }
                arr.add(toAdd);
            }
        }
        br.close();
        return least.compare;
    }
    
    /**
     * Finds the Levenshtein Distance between two strings, equal to the number
     * of character additions, deletions, or substitutions required to make
     * one string equal to another
     * @param input   The input string
     * @param compare The string input is being compared to
     * @return        The Levenshtein Distance between the two strings
     */
    public int minimumEditDistance(String input, String compare) {
        int len1 = input.length();
        int len2 = compare.length();
        int[][] arr = new int[len2 + 1][len1 + 1];
        for (int i = 0; i < len1 + 1; i++) {
            arr[0][i] = i;
        }
        for (int i = 0; i < len2 + 1; i++) {
            arr[i][0] = i;
        }
        for (int i = 1; i <= len2; i++) {
            for (int j = 1; j <= len1; j++) {
                arr[i][j] = Math.min(
                        Math.min(arr[i][j - 1] + 1, arr[i - 1][j] + 1),
                        arr[i - 1][j - 1]
                            + (input.charAt(j - 1) == compare.charAt(i - 1) ? 0 : 1));
            }
        }
        return arr[len2][len1];
    }
    
    /**
     * A custom data type that stores the Levenshtein Distance between two
     * strings, and the string being compared to.
     */
    private class MEDValue implements Comparable<MEDValue> {
        int med;
        String compare;
        public MEDValue(int med, String compare) {
            this.med = med;
            this.compare = compare;
        }
        
        public int compareTo(MEDValue o) {
            if (this.med == o.med) {
                return this.compare.compareTo(o.compare);
            }
            return this.med - o.med;
        }
    }
    
    public static void main(String[] args) throws IOException {
        SpellChecker sc = new SpellChecker();
        for (String s : args) {
            String corrected = sc.spellCheck(s);
            if (corrected.equals(s)) {
                System.out.println("Your word, " + s + ", was found in the dictionary");
            } else {
                System.out.println("Your word, " + s + ", was corrected to " + sc.spellCheck(s));
            }
        }
    }
}
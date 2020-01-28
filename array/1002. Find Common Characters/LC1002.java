import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC1002 {
    /**
     * Solution
     * https://www.youtube.com/watch?v=k1iowWJimbg
     * Time Complexity: O(n^2)
     * Space Complexity: O(n)
     * @param A
     * @return
     */
    public List<String> commonChars(String[] A) {
        // define min_freq with all Int.max value
        // define char_freq with all 0 value
        // store all char in each string into char_freq
        // update min_freq compare with char_freq
        // find commons
        
        // define min_feq
        final int freq_size = 26;
        int[] min_freq = new int[freq_size];
        Arrays.fill(min_freq, Integer.MAX_VALUE);
        
        // build char_freq && update min_feq
        for (String s : A) {
            int[] char_freq = new int[freq_size];
            
            // build char_freq
            for (char c : s.toCharArray()) {
                char_freq[c - 'a']++;
            }
            
            // update min_freq
            for (int i = 0; i < freq_size; i++) {
                min_freq[i] = Math.min(min_freq[i], char_freq[i]);
            }
        }
        
        // find commons
        List<String> commons = new ArrayList<>();
        for (int i = 0; i < freq_size; i++) {
            while (min_freq[i] > 0) {
                String common = String.valueOf((char) (i + 'a'));
                commons.add(common);
                min_freq[i]--;
            }
        }
        
        return commons;
    }
    
    public static void main(String[] args) {
        LC1002 solution = new LC1002();
        System.out.println("1002. Find Common Characters [easy]");
        String[][] inputs = {
            {"bella","label","roller"},
            {"cool","lock","cook"}
        };
        String[][] answers = {
            {"e", "l", "l"},
            {"c", "o"}
        };

        for (int i = 0; i < inputs.length; i++) {
            System.out.println("Example " + (i + 1));
            String[] input = inputs[i];
            String[] answer = answers[i];
            List<String> output = solution.commonChars(input);
            System.out.println("Input: " + Arrays.toString(input));
            System.out.println("Output: " + Arrays.toString(output.toArray()));
            System.out.println("Answer: " + Arrays.toString(answer));
            System.out.println();
        }
    }
}
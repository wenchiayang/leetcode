import java.util.Map;
import java.util.HashMap;

public class LC290 {
    /**
     * Solution
     * https://www.youtube.com/watch?v=dnlB0lvz5LY
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * @param pattern
     * @param str
     * @return
     */
    public boolean wordPattern(String pattern, String str) {
        // check corner cases
        if (pattern == null || str == null) {
            return false;
        }

        String[] splited = str.split(" ");

        if (splited.length != pattern.length()) {
            return false;
        }
        
        Map<Character, String> map = new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            char current = pattern.charAt(i);

            // current is in the map
            if (map.containsKey(current)) {
                // compare value with key
                // same current but with diff string -> false
                if (!map.get(current).equals(splited[i])) {
                    return false;
                }
            } else {
                // compare key with value
                // string can only map 1 char in the map
                // pattern: "abba", str = "dog dog dog dog"
                // false
                if (map.containsValue(splited[i])) {
                    return false;
                }

                map.put(current, splited[i]);
            }
        }

        return true;
    }
    
    public static void main(String[] args) {
        System.out.println("290. Word Pattern [easy]");

        String[] patterns = {"abba", "abba", "aaaa", "abba"};
        String[] strs = {"dog cat cat dog", "dog cat cat fish", "dog cat cat dog", "dog dog dog dog"};
        boolean[] answers = {true, false, false, false};
        LC290 solution = new LC290();

        for (int i = 0; i < patterns.length; i++) {
            System.out.println("Example " + (i + 1) + ":");
            String pattern = patterns[i];
            String str = strs[i];
            boolean output = solution.wordPattern(pattern, str);
            boolean answer = answers[i];
            boolean score = output == answer;

            System.out.println("Input: pattern = " + pattern + ", str = " + str);
            System.out.println("Output: " + output);
            System.out.println("Answer: " + answer);
            System.out.println("Score: " + score);
            System.out.println();
        }
    }
}
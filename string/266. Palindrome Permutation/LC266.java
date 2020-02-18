import java.util.Map;
import java.util.HashMap;

public class LC266 {
    /**
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * @param s
     * @return
     */
    public boolean canPermutePalindrome(String s) {
        // check corner case
        if (s == null) {
            return false;
        }

        Map<Character, Integer> counter = new HashMap<Character, Integer>();
        for (char c : s.toCharArray()) {
            int count = counter.getOrDefault(c, 0);
            counter.put(c, count + 1);
        }

        int odd_count = 0;
        for (char c : counter.keySet()) {
            if (counter.get(c) % 2 == 1) {
                odd_count++;
            }
        }

        return odd_count == 1 || odd_count == 0;
    }
    
    public static void main(String[] args) {
        System.out.println("242. Valid Anagram [easy]");

        String[] inputs = {"code", "aab", "carerac", "aa", "abc"};
        boolean[] answers = {false, true, true, true, false};
        LC266 solution = new LC266();

        for (int i = 0; i < inputs.length; i++) {
            System.out.println("Example " + (i + 1) + ":");
            String s = inputs[i];
            boolean output = solution.canPermutePalindrome(s);
            boolean answer = answers[i];
            boolean score = output == answer;

            System.out.println("Input: " + s);
            System.out.println("Output: " + output);
            System.out.println("Answer: " + answer);
            System.out.println("Score: " + score);
            System.out.println();
        }
    }
}
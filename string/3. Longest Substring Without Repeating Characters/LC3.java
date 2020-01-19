import java.util.HashSet;
import java.util.Set;

/**
 * LC3
 */
public class LC3 {
    /**
     * Solution(Sliding Window)
     * https://www.youtube.com/watch?v=3IETreEybaA
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        int start = 0, end = 0;
        int longest = 0;
        Set<Character> set = new HashSet<>();
        
        while (end < s.length()) {
            char c = s.charAt(end);

            if (!set.contains(c)) {
                set.add(c);
                end++;
                longest = Math.max(longest, set.size());
            } else {
                set.remove(s.charAt(start));
                start++;
            }
        }

        return longest;
    }

    public static void main(String[] args) {
        LC3 solution = new LC3();
        String[] inputs = {
            "abcabcbb", "bbbbb", "pwwkew", " ", "au", "dvdf"
        };
        int[] answers = {3, 1, 3, 1, 2, 3};

        System.out.println("3. Longest Substring Without Repeating Characters [medium]");

        for (int i = 0; i < inputs.length; i++) {
            String s = inputs[i];
            int answer = answers[i];
            int output = solution.lengthOfLongestSubstring(s);

            System.out.println("Example " + (i + 1) + ":");
            System.out.println("Input: " + s);
            System.out.println("Output: " + output);
            System.out.println("Answer: " + answer);
            System.out.println("Score: " + (answer == output));
            System.out.println();
        }
    }
}
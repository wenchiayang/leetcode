import java.util.Arrays;
import java.util.Set;

public class LC14 {
    /**
     * Solution
     * https://www.youtube.com/watch?v=1YQmI7F9dJ0
     * Time Complexity: O(S), where S is the sum of 
     * all characters in all strings. In the worst 
     * case there will be n equal strings with length m
     * and the algorithm performs S = m * n character 
     * comparisons. Even though the worst case is 
     * still the same as Approach 1, in the best case 
     * there are at most n * minLen comparisons where 
     * minLen is the length of the shortest string 
     * in the array.
     * Space Complexity: O(1)
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        String longest = "";

        // check corner cases
        if (strs == null || strs.length == 0) {
            return longest;
        }

        int index = 0;

        // start from string at array[0]
        for (char c : strs[0].toCharArray()) {
            for (int i = 1; i < strs.length; i++) {
                // break conditions
                // 1. out of bounds: index >= len(str) at in array[i]
                // 2. c is not match
                if (index >= strs[i].length() || c != strs[i].charAt(index)) {
                    return longest;
                }
            }

            longest += c;
            index++;
        }

        return longest;
    }

    public static void main(String[] args) {
        LC14 solution = new LC14();
        System.out.println("14. Longest Common Prefix [easy]");
        String[][] inputs = {
            {"flower","flow","flight"},
            {"dog","racecar","car"}
        };
        String[] answers = {"fl", ""};

        for (int i = 0; i < inputs.length; i++) {
            System.out.println("Example " + (i + 1) + ":");
            String[] strs = inputs[i];
            String output = solution.longestCommonPrefix(strs);
            String answer = answers[i];
            boolean score = output.equals(answer);

            System.out.println("Input: " + Arrays.toString(strs));
            System.out.println("Output: " + output);
            System.out.println("Answer: " + answer);
            System.out.println("Score: " + score);
            System.out.println();
        }
    }
}
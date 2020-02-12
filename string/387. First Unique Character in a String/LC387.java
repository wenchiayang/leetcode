import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

public class LC387 {
    /**
     * Solution
     * https://blog.csdn.net/zj15527620802/article/details/80365036
     * char array
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * @param s
     * @return
     */
    public int firstUniqChar(String s) {
        int[] counter = new int[26];
        
        // create char counter
        for (int i = 0; i < s.length(); i++) {
            int position = s.charAt(i) - 'a';
            counter[position]++;
        }

        // find the index
        for (int i = 0; i < s.length(); i++) {
            int position = s.charAt(i) - 'a';
            if (counter[position] == 1) {
                return i;
            }
        }

        return -1;
    }

    /**
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * @param s
     * @return
     */
    public int firstUniqChar2(String s) {
        Map<Character, Integer> counter = new HashMap<>();
        int len = s.length();
        // build hash map : character and how often it appears
        for (char c : s.toCharArray()) {
            int count = counter.getOrDefault(c, 0);
            counter.put(c, count + 1);
        }
        
        // find the index
        for (int i = 0; i < len; i++) {
            if (counter.get(s.charAt(i)) == 1) {
                return i;
            }
        }
        
        return -1;
    }

    public static void main(String[] args) {
        LC387 solution = new LC387();
        System.out.println("387. First Unique Character in a String [easy]");
        String[] inputs = {"leetcode", "loveleetcode"};
        int[] answers = {0, 2};

        for (int i = 0; i < inputs.length; i++) {
            System.out.println("Example " + (i + 1) + ":");
            String s = inputs[i];
            int output = solution.firstUniqChar(s);
            int answer = answers[i];
            boolean score = output == answer;

            System.out.println("Input: " + s);
            System.out.println("Output: " + output);
            System.out.println("Answer: " + answer);
            System.out.println("Score: " + score);
            System.out.println();
        }
    }
}
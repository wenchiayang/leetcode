public class LC409 {
    /**
     * Solution
     * Greedy
     * https://leetcode.com/problems/longest-palindrome/solution/
     * Time Complexity: O(n)
     * Space Complexity: O(128) = O(1), 128 is the size of ASCII table
     * @param s
     * @return
     */
    public int longestPalindrome(String s) {
        // check corner cases
        if (s == null || s.length() == 0) return 0;

        // counter of every char in s
        int[] counter = new int[128];
        for (char c : s.toCharArray()) {
            counter[c]++;
        }

        // find answer
        int answer = 0;

        for (int count : counter) {
            if (count % 2 == 0) { // even
                answer += count;
            } else { // odd, only add even parts
                answer += count - 1;
            }

            // add the last c of when count is odd
            // it will only be added once
            if (answer % 2 == 0 && count % 2 == 1) {
                answer++;
            }
        }

        return answer;
    }
    
    public static void main(String[] args) {
        LC409 solution = new LC409();
        System.out.println("409. Longest Palindrome [easy]");
        String input = "abccccdd";
        int answer = 7;
        System.err.println("Example:");
        int output = solution.longestPalindrome(input);
        System.out.println("Input: " + input);
        System.out.println("Output: " + output);
        System.out.println("Answer: " + answer);
        System.out.println("Score: " + (output == answer));
    }
}
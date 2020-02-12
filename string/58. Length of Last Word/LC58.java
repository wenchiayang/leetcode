
public class LC58 {
    /**
     * Solution
     * https://www.youtube.com/watch?v=L1Gm5SWFDhs
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * @param s
     * @return
     */
    public int lengthOfLastWord(String s) {
        int previous_count = 0;
        int curr_count = 0;

        // need to consider "Hello "
        for (int i = 0; i < s.length(); i++) {
            // might have 2 cases
            // 1. "Hello Word", the space between 2 char
            // 2. "Hello ", the space of the last char
            if (s.charAt(i) == ' ') {
                curr_count = 0;
            } else {
                curr_count++;
                // set curr count to previous count
                previous_count = curr_count;
            }
        }

        return previous_count;
    }
    
    public static void main(String[] args) {
        LC58 solution = new LC58();
        System.out.println("58. Length of Last Word [easy]");
        System.out.println("Example:");
        String s = "Hello World";
        int output = solution.lengthOfLastWord(s);
        int answer = 5;
        boolean score = output == answer;

        System.out.println("Input: " + s);
        System.out.println("Output: " + output);
        System.out.println("Answer: " + answer);
        System.out.println("Score: " + score);
    }
}
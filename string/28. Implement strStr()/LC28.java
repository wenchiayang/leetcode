public class LC28 {

    /**
     * Solution
     * https://www.youtube.com/watch?v=bzoCm_w8yx0
     * Time Complexity: O((N-L)*L), where N is a length of haystack and L is a length of needle.
     * Space Complexity: O(1)
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        // check corner cases
        if (haystack == null || needle == null) {
            return -1;
        }

        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
            int j;
            for (j = 0; j < needle.length(); j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    break;
                }
            }

            if (j == needle.length()) {
                return i;
            }
        }

        return -1;
    }

    /**
     * Solution
     * Substring
     * https://www.youtube.com/watch?v=HhUWIqA-CuE
     * Time Complexity: O(n^2), because string.substring() is O(n), and we loop through the length of haystack
     * Space Complexity: O(1)
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr2(String haystack, String needle) {
        // corner case -> needle is empty
        if (needle.length() == 0) return 0;

        // find check range from 0 to len(haystack) - len(needle)
        int check_range = haystack.length() - needle.length();
        for (int i = 0; i <= check_range; i++) {
            String haystack_sub = haystack.substring(i, i + needle.length());
            if (haystack_sub.equals(needle)) {
                return i;
            }
        } 

        return -1;
    }
    
    public static void main(String[] args) {
        LC28 solution = new LC28();
        System.out.println("28. Implement strStr() [easy]");
        String[] haystacks = {"mississippi", "", "hello", "aaaaa"};
        String[] needles = {"issip", "", "ll", "bba"};
        int[] answers = {4, 0, 2, -1};

        for (int i = 0; i < haystacks.length; i++) {
            System.err.println("Example " + (i + 1) + ":");
            String haystack = haystacks[i];
            String needle = needles[i];
            int answer = answers[i];
            int output = solution.strStr(haystack, needle);
            System.out.println("Input: haystack = " + haystack + ", needle = " + needle);
            System.out.println("Output: " + output);
            System.out.println("Answer: " + answer);
            System.out.println("Score: " + (output == answer));
            System.out.println();
        }
    }
}
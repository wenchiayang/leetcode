
public class LC541 {
    /**
     * Solution
     * https://www.polarxiong.com/archives/LeetCode-541-reverse-string-ii.html
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public String reverseStr(String s, int k) {
        char[] array = s.toCharArray();

        // i jump every 2 * k steps: i += 2 * k
        // i = 0 -> 4(0 + 2 * k) -> 8(4 + 2 * k)... 
        for (int i = 0; i < s.length(); i += 2 * k) {
            // check if i + k >= len(s)
            // if yes -> reverse from i to len(s) - 1
            // if no -> reverse from i to i + k - 1
            if (i + k >= s.length()) {
                reverse(array, i, s.length() - 1);
                System.out.println("reverse: (" + i + "," + (s.length() - 1) + ")");
            } else {
                reverse(array, i, i + k - 1);
                System.out.println("reverse: (" + i + "," + (i + k - 1) + ")");
            }
        }
        
        return new String(array);
    }

    private void reverse(char[] array, int start, int end) {
        while (start < end) {
            char temp = array[start];
            array[start] = array[end];
            array[end] = temp;
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        System.out.println("541. Reverse String II [easy]");
        LC541 solution = new LC541();

        String[] inputs = {
            "abcdefg", "ab", "abcdef"
        };

        int[] ks = {2, 3, 4};
        String[] answers = {
            "bacdfeg", "ba", "dcbaef"
        };

        for (int i = 0; i < inputs.length; i++) {
            System.out.println("Example " + (i + 1) + ":");

            String s = inputs[i];
            int k = ks[i];
            String output = solution.reverseStr(s, k);
            String answer = answers[i];
            boolean score = output.equals(answer);

            System.out.println("Input: s = " + s + ", k = " + k);
            System.out.println("Output: " + output);
            System.out.println("Answer: " + answer);
            System.out.println("Score: " + score);
            System.out.println();
        }
    }
}
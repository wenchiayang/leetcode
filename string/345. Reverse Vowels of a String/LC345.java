import java.util.Stack;

public class LC345 {
    /**
     * Solution
     * https://www.youtube.com/watch?v=TFsEXWxeLVI
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * @param s
     * @return
     */
    public String reverseVowels(String s) {
        // check corner case
        if (s == null || s.length() == 0) {
            return "";
        }

        int left = 0, right = s.length() - 1;
        char[] array = s.toCharArray();

        while (left < right) {
            if (!isVowel(array[left])) {
                left++;
            } else if (!isVowel(array[right])) {
                right--;
            } else {
                // swap left, right
                char temp = array[left];
                array[left] = array[right];
                array[right] = temp;
                left++;
                right--;
            }
        }

        return new String(array);
    }

    /**
     * Naive
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * @param s
     * @return
     */
    public String reverseVowels2(String s) {
        Stack<Character> stack = new Stack<>();
        char[] array = s.toCharArray();

        for (char c : s.toCharArray()) {
            if (isVowel(c)) {
                stack.push(c);
            }
        }

        for (int i = 0; i < array.length; i++) {
            if (isVowel(array[i])) {
                array[i] = stack.pop();
            }
        }

        return new String(array);
    }

    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' ||
                c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U';
    }
    
    public static void main(String[] args) {
        System.out.println("345. Reverse Vowels of a String [easy]");
        LC345 solution = new LC345();

        String[] inputs = {"hello", "leetcode", "aA"};
        String[] answers = {"holle", "leotcede", "Aa"};

        for (int i = 0; i < inputs.length; i++) {
            System.out.println("Example " + (i + 1) + ":");
            String s = inputs[i];
            String output = solution.reverseVowels(s);
            String answer = answers[i];
            boolean score = output.equals(answer);

            System.out.println("Input: " + s);
            System.out.println("Output: " + output);
            System.out.println("Answer: " + answer);
            System.out.println("Score: " + score);
            System.out.println();
        }
    }
}
import java.util.Arrays;

public class LC344 {
    /**
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public void reverseString(char[] s) {
        for (int i = 0; i < s.length / 2; i++) {
            char temp = s[i];
            s[i] = s[s.length - i - 1];
            s[s.length - i - 1] = temp;
        }
    }

    public static void main(String[] args) {
        LC344 solution = new LC344();
        System.out.println("344. Reverse String [easy]");
        char[][] inputs = {
            {'h', 'e', 'l', 'l', 'o'},
            {'H', 'a', 'n', 'n', 'a', 'h'}
        };
        char[][] answers = {
            {'o', 'l', 'l', 'e', 'h'},
            {'h', 'a', 'n', 'n', 'a', 'H'}
        };

        for (int i = 0; i < inputs.length; i++) {
            System.out.println("Example " + (i + 1) + ":");
            char[] s = inputs[i];
            System.out.println("Input: " + Arrays.toString(s));
            solution.reverseString(s);
            char[] answer = answers[i];
            System.out.println("Output: " + Arrays.toString(s));
            System.out.println("Answer: " + Arrays.toString(answer));
            System.out.println();
        }
    }
}
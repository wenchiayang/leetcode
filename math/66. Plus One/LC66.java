import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Arrays;

public class LC66 {
    /**
     * Solution
     * https://www.youtube.com/watch?v=_sls9AdBymI
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * @param digits
     * @return
     */
    public int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            // digits[i] is in 0..<9
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }

            // digits[i] is 9
            digits[i] = 0;
        }

        // if not result
        // ex: 99 + 1 -> 00 now
        // need to add carry
        int[] result = new int[digits.length + 1];
        result[0] = 1;
        return result;
    }

    /**
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * @param digits
     * @return
     */
    public int[] plusOne2(int[] digits) {
        List<Integer> result = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();

        for (int d : digits) {
            stack.push(d);
        }

        int digit = stack.pop() + 1;
        int carry = digit > 9 ? 1 : 0;
        result.add(digit % 10);

        while (!stack.isEmpty()) {
            digit = stack.pop();

            if (carry == 1) {
                digit += carry;

                if (digit > 9) {
                    carry = 1;
                    digit = 0;
                } else {
                    carry = 0;
                }
            }

            result.add(digit);
        }

        if (carry == 1) {
            result.add(carry);
        }

        int[] answer = new int[result.size()];
        int index = 0;
        for (int i = result.size() - 1; i >= 0; i--) {
            answer[index++] = result.get(i);
        }

        return answer;
    }
    
    public static void main(String[] args) {
        System.out.println("66. Plus One [easy]");
        LC66 solution = new LC66();

        int[][] inputs = {
            {8, 9, 9, 9}, {1, 2, 3}, {4, 3, 2, 1}, {9, 9}
        };

        int[][] answers = {
            {9, 0, 0, 0}, {1, 2, 4}, {4, 3, 2, 2}, {1, 0, 0}
        };

        for (int i = 0; i < answers.length; i++) {
            System.out.println("Example " + (i + 1) + ":");
            int[] digits = inputs[i];
            int[] output = solution.plusOne(digits);
            int[] answer = answers[i];

            System.out.println("Input: " + Arrays.toString(digits));
            System.out.println("Output: " + Arrays.toString(output));
            System.out.println("Answer: " + Arrays.toString(answer));
            System.out.println();
        }
    }
}
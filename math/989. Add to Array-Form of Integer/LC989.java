import java.util.List;
import java.util.Stack;
import java.util.ArrayList;
import java.util.Arrays;

public class LC989 {
    /**
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * @param A
     * @param K
     * @return
     */
    public List<Integer> addToArrayForm(int[] A, int K) {
        List<Integer> result = new ArrayList<>();
        
        // check corner cases
        if (A == null || A.length == 0) {
            return result;
        }

        int carry = 0;
        int indexA = A.length - 1;

        while (indexA >= 0 || K != 0) {
            int digit = 0;
            digit += carry;

            // if A has value -> add to digit
            if (indexA >= 0) {
                digit += A[indexA];
                indexA--;
            }

            // if K > 0, add to digit
            if (K != 0) {
                digit += K % 10;
                K /= 10;
            }

            // calculate carry
            if (digit > 9) {
                carry = 1;
                digit %= 10;
            } else {
                carry = 0;
            }

            result.add(digit);
        }

        // overflow, need to add to digit
        if (carry == 1) {
            result.add(carry);
        }

        // get correct answer from reverse
        reverse(result);
        return result;
    }

    private void reverse(List<Integer> list) {
        int left = 0, right = list.size() - 1;

        while (left < right) {
            int temp = list.get(left);
            list.set(left, list.get(right));
            list.set(right, temp);

            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        System.out.println("989. Add to Array-Form of Integer [easy]");
        LC989 solution = new LC989();

        int[][] as = {
            {0}, {1, 2, 0, 0}, {2, 7, 4}, {2, 1, 5}, {9, 9, 9, 9, 9, 9, 9, 9, 9, 9}
        };
        int[] ks = {10000, 34, 181, 806, 1};
        int[][] answers = {
            {1, 0, 0, 0, 0}, {1, 2, 3, 4}, {4, 5, 5}, {1, 0, 2, 1}, {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };

        for (int i = 0; i < answers.length; i++) {
            System.out.println("Example " + (i + 1) + ":");
            int[] A = as[i];
            int K = ks[i];
            List<Integer> output = solution.addToArrayForm(A, K);
            int[] answer = answers[i];

            System.out.println("Input: A = " + Arrays.toString(A) + ", K = " + K);
            System.out.println("Output: " + Arrays.toString(output.toArray()));
            System.out.println("Answer: " + Arrays.toString(answer));
            System.out.println();
        }
    }
}
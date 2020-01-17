import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * LC268
 */
public class LC268 {
    // Solution(calculate sum)
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public int missingNumber(int[] nums) {
        // sum of first n numbers
        int n = nums.length + 1;
        int expected_sum = n * (n - 1) / 2;

        // current sum
        int current_sum = 0;

        for (int num : nums) {
            current_sum += num;
        }

        return expected_sum - current_sum;
    }

    // Brute Force
    // Time Complexity: O(n)
    // Space Complexity: O(n)
    public int missingNumber2(int[] nums) {
        // store all nums in the set
        Set<Integer> set = new HashSet<>();
        
        for (int n : nums) {
            set.add(n);
        }

        // compare and find missing
        int missing = -1;

        for (int i = 0; i <= nums.length; i++) {
            if (!set.contains(i)) {
                missing = i;
            }
        }

        return missing;
    }

    public static void main(String[] args) {
        LC268 solution = new LC268();
        int[][] inputs = {
            {0},
            {0, 1},
            {3, 0, 1},
            {9, 6, 4, 2, 3, 5, 7, 0, 1}
        };
        int[] answers = {1, 2, 2, 8};

        System.out.println("268. Missing Number [easy]");
        for (int i = 0; i < inputs.length; i++) {
            System.out.println("Example: " + (i + 1));
            System.out.println("Input: " + Arrays.toString(inputs[i]));
            System.out.println("Output: " + solution.missingNumber(inputs[i]));
            System.out.println("Answer: " + answers[i]);
            System.out.println();
        }
    }
}
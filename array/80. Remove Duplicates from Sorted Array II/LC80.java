import java.util.Arrays;

/**
 * LC80
 */
public class LC80 {
    // Two Pointers
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public int removeDuplicates(int[] nums) {
        // check edge cases
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int left = 2;

        // start from index 2(3rd element), because the firse 2 elements are always valid
        for (int right = 2; right < nums.length; ++right) {
            if (nums[left - 2] != nums[right]) {
                nums[left++] = nums[right];
            }
        }
        
        return left;
    }

    public static void main(String[] args) {
        LC80 solution = new LC80();
        int[][] inputs = {
            {0, 0, 1, 1, 1, 1, 2, 3, 3},
            {1, 1, 1, 2, 2, 3},
            {0, 0, 1, 1, 1, 1, 2, 3, 3}
        };
        
        for (int i = 0; i < inputs.length; i++) {
            System.out.println("Example: " + (i + 1));
            System.out.println("Given nums = " + Arrays.toString(inputs[i]));
            System.out.println("Output: " + solution.removeDuplicates(inputs[i]));
            System.out.println();
        }
    }
}
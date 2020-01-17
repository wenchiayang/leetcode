import java.util.Arrays;

/**
 * LC27
 */
public class LC27 {
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public int removeElement(int[] nums, int val) {
        // check edge cases
        if (nums == null || nums.length < 1) {
            return 0;
        }

        // only add valid element and start from the beginning
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[index++] = nums[i];
            }
        }
        
        return index;
    }

    public static void main(String[] args) {
        LC27 solution = new LC27();
        int[] vals = {3, 2};
        int[][] inputs = {
            {3, 2, 2, 3},
            {0, 1, 2, 2, 3, 0, 4, 2}
        };
        
        for (int i = 0; i < inputs.length; i++) {
            System.out.println("Example: " + (i + 1));
            System.out.println("Given nums = " + Arrays.toString(inputs[i]) + ", val = " + vals[i]);
            System.out.println("Output: " + solution.removeElement(inputs[i], vals[i]));
            System.out.println();
        }
    }
    
}
import java.util.Arrays;

/**
 * LC41
 */
public class LC41 {
    /**
     * Solution
     * https://www.youtube.com/watch?v=cG1rZPIo3ww
     * https://www.youtube.com/watch?v=cG1rZPIo3ww
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * @param nums
     * @return
     */
    public int firstMissingPositive(int[] nums) {
        // check edge cases
        if (nums == null || nums.length == 0) {
            return 1;
        }
        
        // put all elements into their expected index
        for (int i = 0; i < nums.length; i++) {
            
            // nums[nums[i] - 1] != nums[i] means wrong index
            // we continue to swap index 
            // util they're all in the right indexs
            while (nums[i] > 0 && nums[i] <= nums.length && nums[nums[i] - 1] != nums[i]) {
                swap(nums, nums[i] - 1, i);
            }
        }
        
        for (int i = 0; i < nums.length; i++) {
            int expected = i + 1;
            if (nums[i] != expected) {
                return expected;
            }
        }
        
        // rest cases are invalid case
        return nums.length + 1;
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        LC41 solution = new LC41();
        int[][] inputs = {
            {1, 2, 0},
            {3, 4, -1, 1},
            {7, 8, 9, 11, 12},
            {7, 8, 9},
            {1, 2, 3, 4}
        };
        int[] answers = {3, 2, 1, 1, 5};

        System.out.println("41. First Missing Positive [hard]");
        for (int i = 0; i < inputs.length; i++) {
            System.out.println("Example: " + (i + 1));
            System.out.println("Input: " + Arrays.toString(inputs[i]));
            int output = solution.firstMissingPositive(inputs[i]);
            int answer = answers[i];
            System.out.println("Output: " + output);
            System.out.println("Answer: " + answer);
            System.out.println("Score: " + (answer == output));
            System.out.println();
        }
    }
}
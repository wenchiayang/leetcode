import java.util.Arrays;

public class LC283 {
    /**
     * Solution
     * https://www.youtube.com/watch?v=1PEncepEIoE
     * Place Non-Zero elements first, and then zeros
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        // check corner cases
        if (nums == null || nums.length == 0) return;

        // step1: put none-zero elements first
        // step2: put rest elements zeros
        int noneZeroIndex = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[noneZeroIndex++] = nums[i];
            }
        }

        for (int i = noneZeroIndex; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    public static void main(String[] args) {
        LC283 solution = new LC283();
        System.out.println("283. Move Zeroes [easy]");
        int[] nums = {0, 1, 0, 3, 12};
        int[] answer = {1, 3, 12, 0, 0};
        System.out.println("Example:");
        System.out.println("Input: " + Arrays.toString(nums));
        solution.moveZeroes(nums);
        System.out.println("Output: " + Arrays.toString(nums));
        System.out.println("Answer: " + Arrays.toString(answer));
    }
}
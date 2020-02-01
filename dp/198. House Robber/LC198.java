import java.util.Arrays;

public class LC198 {
    /**
     * Solution
     * Dynamic Programming
     * https://www.youtube.com/watch?v=ZwDDLAeeBM0
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        // check corner cases
        if (nums == null || nums.length == 0) return 0;

        int[] dp = new int[nums.length + 1];
        dp[0] = 0;
        dp[1] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            dp[i + 1] = Math.max(dp[i], dp[i - 1] + nums[i]);
        }

        return dp[nums.length];
    }

    /**
     * Solution
     * Dynamic Programming
     * https://www.youtube.com/watch?v=xlvhyfcoQa4
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * @param nums
     * @return
     */
    public int rob2(int[] nums) {
        // consider the corner cases
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);

        // use dp to calculate max
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            int curr = nums[i];
            int prevprev = dp[i - 2];
            int prev = dp[i - 1];
            dp[i] = Math.max(curr + prevprev, prev);
        }

        return dp[nums.length - 1];
    }

    public static void main(String[] args) {
        LC198 solution = new LC198();
        int[][] inputs = {
            {1, 2, 3, 1},
            {2, 7, 9, 3, 1},
            {2, 1, 1, 2}
        };
        int[] answers = {4, 12, 4};
        System.out.println("198. House Robber [easy]");

        for (int i = 0; i < inputs.length; i++) {
            System.out.println("Example " + (i + 1) + ":");
            int[] nums = inputs[i];
            int output = solution.rob(nums);
            int answer = answers[i];
            System.out.println("Input: " + Arrays.toString(nums)); 
            System.out.println("Output: " + output);
            System.out.println("Answer: " + answer);
            System.out.println("Score: " + (answer == output));
            System.out.println();
        }
    }
}
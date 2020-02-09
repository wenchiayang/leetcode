import java.util.Arrays;

public class LC287 {
    /**
     * Solution
     * Find Circle in the linkedlist II
     * https://www.bilibili.com/video/av62810942/
     * https://zxi.mytechroad.com/blog/algorithms/binary-search/leetcode-287-find-the-duplicate-number/
     * https://www.youtube.com/watch?v=i4kBcvA3OV4
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public int findDuplicate(int[] nums) {
        // check corner cases
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int slow = 0, fast = 0;

        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);

        slow = 0;
        while (fast != slow) {
            slow = nums[slow];
            fast = nums[fast];
        }

        return fast; // or slow is okay, too;
    }

    /**
     * Naive Method
     * Time Complexity: O(n ^ 2)
     * Space Complexity: O(1)
     */
    public int findDuplicate2(int[] nums) {
        // check corner cases
        if (nums == null || nums.length == 0) {
            return 0;
        }

        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i == j) continue;

                if (nums[i] == nums[j]) {
                    return nums[i];
                }
            }
        }

        return 0;
    }
    
    public static void main(String[] args) {
        System.out.println("287. Find the Duplicate Number [medium]");
        LC287 solution = new LC287();
        int[][] inputs = {
            {1, 3, 4, 2, 2}, {3, 1, 3, 4, 2}, {2, 2, 2, 2, 2}
        };
        int[] answers = {2, 3, 2};

        for (int i = 0; i < inputs.length; i++) {
            int[] nums = inputs[i];
            int output = solution.findDuplicate(nums);
            int answer = answers[i];
            boolean score = answer == output;

            System.out.println("Example " + (i + 1) + ":");
            System.out.println("Input: " + Arrays.toString(nums));
            System.out.println("Output: " + output);
            System.out.println("Answer: " + answer);
            System.out.println("Score: " + score);
            System.out.println();
        }
    }
}
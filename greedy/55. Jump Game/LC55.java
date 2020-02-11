import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LC55 {
    /**
     * Solution
     * https://www.youtube.com/watch?v=Zb4eRjuPHbM
     * Greedy
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        // check corner cases
        if (nums == null || nums.length == 0) {
            return false;
        }

        // an index that we'll update to keep last position
        // that we can reach to the end of the array
        int lastGoodIndexPosition = nums.length - 1;

        // we loop the array from backward
        for (int i = nums.length - 1; i >= 0; i--) {
            // only update lastGoodIndexPosition when
            // current index + nums[i](path that we can go) > lastGoodIndexPosition
            if (i + nums[i] >= lastGoodIndexPosition) {
                lastGoodIndexPosition = i;
            }
        }

        // lastGoodIndexPosition = 0 means we can go to 
        // last index from index i = 0 to len(nums) - 1
        return lastGoodIndexPosition == 0;
    }

    /**
     * Solution
     * https://www.youtube.com/watch?v=r3pZd9ghqxk
     * Find max reach index
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * @param nums
     * @return
     */
    public boolean canJump2(int[] nums) {
        // check corner cases
        if (nums == null || nums.length == 0) {
            return false;
        }

        int max_reach = 0;

        // loop the entire array
        // make sure current index: i must less than or eaqual to max_reach
        // if i > max_reach -> impossible to reach the end -> break the loop
        for (int i = 0; i < nums.length && i <= max_reach; i++) {
            // nums[i] + i -> max index that we can reach
            max_reach = Math.max(nums[i] + i, max_reach);

            // we can reach at the end of the array
            if (max_reach >= nums.length - 1) {
                return true;
            }
        }

        // max_reach can't reach the end of the array
        return false;
    }
    
    public static void main(String[] args) {
        System.out.println("55. Jump Game [medium]");
        LC55 solution = new LC55();

        int[][] inputs = {
            {2, 3, 1, 1, 4},
            {3, 2, 1, 0, 4}
        };
        boolean[] answers = {true, false};

        for (int i = 0; i < inputs.length; i++) {
            int[] nums = inputs[i];
            boolean output = solution.canJump(nums);
            boolean answer = answers[i];
            boolean score = output == answer;

            System.out.println("Example " + (i + 1) + ":");
            System.out.println("Input:" + Arrays.toString(nums));
            System.out.println("Output: " + output);
            System.out.println("Answer: " + answer);
            System.out.println("Score: " + score);
            System.out.println();
        }
    }
}
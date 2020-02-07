import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LC219 {
    /**
     * Solution
     * https://www.youtube.com/watch?v=Gaszkdd0RZA
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * @param nums
     * @param k
     * @return
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        // check corner cases
        if (nums == null || nums.length == 0) {
            return false;
        }

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int current = nums[i];

            if (map.containsKey(current) && (i - map.get(current) <= k)) {
                return true;
            } else {
                map.put(current, i);
            }
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println("219. Contains Duplicate II [easy]");
        int[][] inputs = {
            {99, 99}, {1, 2, 3, 1}, {1, 0, 1, 1}, {1, 2, 3, 1, 2, 3}, {1}
        };
        int[] ks = {2, 3, 1, 2, 1};
        boolean[] answers = {true, true, true, false, false};

        LC219 solution = new LC219();
        for (int i = 0; i < inputs.length; i++) {
            int[] nums = inputs[i];
            int k = ks[i];
            boolean output = solution.containsNearbyDuplicate(nums, k);
            boolean answer = answers[i];
            boolean score = output == answer;
            System.out.println("Input: " + Arrays.toString(nums) + ", k = " + k);
            System.out.println("Output: " + output);
            System.out.println("Answer: " + answer);
            System.out.println("Score: " + score);
            System.out.println();
        }
    }
}
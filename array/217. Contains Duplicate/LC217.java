import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LC217 {
    /**
     * Brute Force
     * Sort
     * Time Complexity: O(nlogn)
     * Space Complexity: O(1)
     * @param nums
     * @return
     */
    public boolean containsDuplicate(int[] nums) {
        // check corner case
        if (nums == null || nums.length == 0) {
            return false;
        }

        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                return true;
            }
        }

        return false;
    }

    /**
     * Brute Force
     * Hash Set
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * @param nums
     * @return
     */
    public boolean containsDuplicate2(int[] nums) {
        // check corner case
        if (nums == null || nums.length == 0) {
            return false;
        }

        Set<Integer> seen = new HashSet<>();

        for (int num : nums) {
            if (seen.contains(num)) {
                return true;
            }

            seen.add(num);
        }

        return false;
    }

    /**
     * Brute Force
     * Hash Table
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * @param nums
     * @return
     */
    public boolean containsDuplicate3(int[] nums) {
        // check corner cases
        if (nums == null || nums.length == 0) {
            return false;
        }

        Map<Integer, Integer> counter = new HashMap<>();

        for (int num : nums) {
            int count = counter.getOrDefault(num, 0);
            counter.put(num, count + 1);
        }

        for (int key : counter.keySet()) {
            if (counter.get(key) > 1) {
                return true;
            }
        }

        return false;
    }
    
    public static void main(String[] args) {
        System.out.println("217. Contains Duplicate [easy]");
        int[][] inputs = {
            {1, 2, 3, 1}, {1, 2, 3, 4}, {1, 1, 1, 3, 3, 4, 3, 2, 4, 2}, {1}
        };
        boolean[] answers = {true, false, true, false};

        LC217 solution = new LC217();
        for (int i = 0; i < inputs.length; i++) {
            int[] nums = inputs[i];
            boolean output = solution.containsDuplicate(nums);
            boolean answer = answers[i];
            boolean score = output == answer;
            System.out.println("Input: " + Arrays.toString(nums));
            System.out.println("Output: " + output);
            System.out.println("Answer: " + answer);
            System.out.println("Score: " + score);
            System.out.println();
        }
    }
}
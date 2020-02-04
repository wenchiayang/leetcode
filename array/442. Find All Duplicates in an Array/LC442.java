import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LC442 {
    /**
     * Solution
     * flip nums[index], check negative
     * https://www.youtube.com/watch?v=aMsSF1Il3IY
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * @param nums
     * @return
     */
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> duplicates = new ArrayList<>();
        // check corner cases
        if (nums == null || nums.length == 0) {
            return duplicates;
        }

        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;

            // if we already visited (negative) -> duplicates
            if (nums[index] < 0) {
                duplicates.add(index + 1);
            }

            // flip nums[index]
            nums[index] = -nums[index];
            // System.out.println("flip: " + index + ", nums: " + Arrays.toString(nums));
        }

        return duplicates;
    }

    /**
     * Brute Force
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * @param nums
     * @return
     */
    public List<Integer> findDuplicates2(int[] nums) {
        List<Integer> duplicates = new ArrayList<>();
        // check corner cases
        if (nums == null || nums.length == 0) {
            return duplicates;
        }
        
        Map<Integer, Integer> counter = new HashMap<>();

        for (int num : nums) {
            int count = counter.getOrDefault(num, 0);
            counter.put(num, count + 1);
        }

        for (int key : counter.keySet()) {
            if (counter.get(key) == 2) {
                duplicates.add(key);
            }
        }

        return duplicates;
    }
    
    public static void main(String[] args) {
        LC442 solution = new LC442();
        System.out.println("442. Find All Duplicates in an Array [medium]");
        int[] nums = {4, 3, 2, 7, 8, 2, 3, 1};
        System.out.println("Example:");
        System.out.println("Input: " + Arrays.toString(nums));
        List<Integer> output = solution.findDuplicates(nums);
        System.out.println("Output: " + Arrays.toString(output.toArray()));
    }
}
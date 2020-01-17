import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

/**
 * LC229
 */
public class LC229 {

    // Solution
    // Boyer-Moore Majority Vote algorithm and my elaboration
    // https://leetcode.com/problems/majority-element-ii/discuss/63520/Boyer-Moore-Majority-Vote-algorithm-and-my-elaboration
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public List<Integer> majorityElement(int[] nums) {
        // check edge cases
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }

        List<Integer> majorities = new ArrayList<>();
        int candidate1 = nums[0], candidate2 = nums[0], count1 = 1, count2 = 0;

        for (int n : nums) {
            if (n == candidate1) {
                count1++;
            } else if (n == candidate2) {
                count2++;
            } else if (count1 == 0) {
                candidate1 = n;
                count1 = 1;
            } else if (count2 == 0) {
                candidate2 = n;
                count2 = 1;
            } else {
                count1--;
                count2--;
            }
        }

        count1 = 0;
        count2 = 0;

        for (int n : nums) {
            if (n == candidate1) {
                count1++;
            }

            // avoid return the same element
            // case occur when nums only contains 1 element
            if (n == candidate2 && candidate1 != candidate2) {
                count2++;
            }
        }

        if (count1 > nums.length / 3) {
            majorities.add(candidate1);
        }

        if (count2 > nums.length / 3) {
            majorities.add(candidate2);
        }

        return majorities;
    }

    // Brute Force
    // Time Complexity: O(n)
    // Space Complexity: O(n)
    public List<Integer> majorityElement2(int[] nums) {
        List<Integer> majorities = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();

        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);

            if (map.get(n) > nums.length / 3) {
                if (!majorities.contains(n)) {
                    majorities.add(n);
                }
            }
        }

        return majorities;
    }

    public static void main(String[] args) {
        LC229 solution = new LC229();
        int[][] inputs = {
            {},
            {3, 2, 3},
            {1, 1, 1, 3, 3, 2, 2, 2},
            {1}
        };
        int[][] answers = {
            {},
            {3}, 
            {1, 2},
            {1}
        };

        System.out.println("229. Majority Element II [medium]");
        for (int i = 0; i < inputs.length; i++) {
            System.out.println("Example: " + (i + 1));
            int[] input = inputs[i];
            System.out.println("Input : " + Arrays.toString(input));
            List<Integer> output = solution.majorityElement(input);
            System.out.println("Output: " + Arrays.toString(output.toArray()));
            System.out.println("Answer: " + Arrays.toString(answers[i]));
            System.out.println();
        }
    }
}
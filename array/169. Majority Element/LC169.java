import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

/**
 * LC169
 */
public class LC169 {
    // Solution: Boyer-Moore Algorithm
    // https://blog.csdn.net/kimixuchen/article/details/52787307
    // https://www.youtube.com/watch?v=8nhk5xXn0jo
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public int majorityElement(int[] nums) {
        int index = 0;
        int count = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[index] == nums[i]) {
                count++;
            } else {
                count--;

                if (count == 0) {
                    index = i;
                    count = 1;
                }
            }

            System.out.println("index, i, count: " + index + ", " + i + ", " + count);
        }

        return nums[index];
    }

    // Hash Map
    // Time Complexity: O(n)
    // Space Complexity: O(n)
    public int majorityElement2(int[] nums) {
        // (key, value) = (num, count)
        Map<Integer, Integer> map = new HashMap<>();

        int max = 0;
        int majarity = -1;
        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);

            if (map.get(n) > max) {
                max = map.get(n);
                majarity = n;
            }
        }

        return majarity;
    }

    public static void main(String[] args) {
        LC169 solution = new LC169();
        int[][] inputs = {
            {3, 2, 3},
            {2, 2, 1, 1, 1, 2, 2}
        };
        int[] answers = {3, 2};

        System.out.println("169. Majority Element [easy]");
        for (int i = 0; i < inputs.length; i++) {
            System.out.println("Example: " + (i + 1));
            int[] input = inputs[i];
            System.out.println("Input : " + Arrays.toString(input));
            int output = solution.majorityElement(input);
            System.out.println("Output: " + output);
            System.out.println("Answer: " + answers[i]);
            System.out.println("Score: " + (output == answers[i]));
            System.out.println();
        }
    }
}
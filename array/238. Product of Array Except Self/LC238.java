import java.util.Arrays;

public class LC238 {
    /**
     * Solution
     * Solve it in constant space
     * https://www.youtube.com/watch?v=tSRFtR3pv74
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public int[] productExceptSelf(int[] nums) {
        int[] products = new int[nums.length];
        products[0] = 1;

        for (int i = 1; i < nums.length; i++) {
            products[i] = products[i - 1] * nums[i - 1];
        }

        int multipler = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            products[i] *= multipler;
            multipler *= nums[i];
        }

        return products;
    }

    /**
     * Solution
     * https://www.youtube.com/watch?v=tSRFtR3pv74
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public int[] productExceptSelf2(int[] nums) {
        int len = nums.length;
        int[] left_products = new int[len];
        int[] right_products = new int[len];
        int[] products = new int[nums.length];
        
        left_products[0] = 1;
        right_products[len - 1] = 1;

        for (int i = 1; i < len; i++) {
            left_products[i] = left_products[i - 1] * nums[i - 1];
        }

        for (int i = len - 2; i >= 0; i--) {
            right_products[i] = right_products[i + 1] * nums[i + 1];
        }

        for (int i = 0; i < len; i++) {
            products[i] = left_products[i] * right_products[i];
        }

        return products;
    }

    public static void main(String[] args) {
        System.out.println("238. Product of Array Except Self [medium]");
        LC238 solution = new LC238();
        int[][] inputs = {
            {4, 5, 1, 8, 2},
            {1, 0},
            {1, 2, 3, 4}
        };
        int[][] answers = {
            {80, 64, 320, 40, 160},
            {0, 1},
            {24, 12, 8, 6}
        };

        for (int i = 0; i < inputs.length; i++) {
            int[] nums = inputs[i];
            int[] answer = answers[i];
            int[] output = solution.productExceptSelf(nums);
            System.out.println("Example:");
            System.out.println("Input: " + Arrays.toString(nums));
            System.out.println("Output: " + Arrays.toString(output));
            System.out.println("Answer: " + Arrays.toString(answer));
            System.out.println();
        }
    }
}
import java.util.Arrays;

/**
 * LC189
 */
public class LC189 {
    // Time Complexity: O(n), where n is the length of nums
    // Space Complexity: O(1)
    public void rotate(int[] nums, int k) {
        k = k % nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    private void reverse(int[] nums, int startIndex, int endIndex) {
        while (startIndex < endIndex) {
            int temp = nums[startIndex];
            nums[startIndex] = nums[endIndex];
            nums[endIndex] = temp;
            startIndex++;
            endIndex--;
        }
    }

    // Brute Force
    // Time Complexity: O(n*k), where n is the length of nums and k is steps to rotate
    // Space Complexity: O(1)
    public void rotate3(int[] nums, int k) {
        // check edges
        if (nums == null || nums.length == 0 || k == 0) {
            return;
        }

        int count = k % nums.length;
        while (count-- > 0) {
            rotateRight(nums);
        }
    }

    // rotate right 1 step
    private void rotateRight(int[] nums) {
        int end = nums[nums.length - 1];

        for (int i = nums.length - 1; i > 0; i--) {
            nums[i] = nums[i - 1];
        }

        nums[0] = end;
    }
    
    public static void main(String[] args) {
        LC189 solution = new LC189();
        int[] ks = {3, 2};
        int[][] inputs = {
            {1, 2, 3, 4, 5, 6, 7},
            {-1, -100, 3, 99}
        };
        
        System.out.println("189. Rotate Array [easy]");
        for (int i = 0; i < inputs.length; i++) {
            System.out.println("Example: " + (i + 1));
            System.out.println("Input: " + Arrays.toString(inputs[i]) + ", k = " + ks[i]);
            solution.rotate(inputs[i], ks[i]);
            System.out.println("Output: " + Arrays.toString(inputs[i]));
            System.out.println();
        }
    }
}
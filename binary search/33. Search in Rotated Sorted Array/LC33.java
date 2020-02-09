import java.util.Arrays;

public class LC33 {
    /**
     * Solution
     * Binary Search
     * https://www.youtube.com/watch?v=QdVrY3stDD4
     * Time Complexity: O(logn)
     * Space Complexity: O(1)
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        // step1: find rotate index
        // step2: setup left, right boundries of binary search
        // step3: binary search

        // check corner cases
        if (nums == null || nums.length == 0) {
            return -1;
        }

        // pivot value is the rotate index
        int pivot = findPivot(nums, 0, nums.length - 1);
        int left = 0;
        int right = nums.length - 1;

        if (target >= nums[pivot] && target <= nums[right]) {
            left = pivot;
        } else {
            right = pivot;
        }
        
        return binarySearch(nums, target, left, right);
    }

    private int findPivot(int[] nums, int left, int right) {
        while (left < right) {
            int middle = left + (right - left) / 2;
            if (nums[middle] > nums[right]) {
                left = middle + 1;
            } else {
                right = middle;
            }
        }

        return left;
    }

    private int binarySearch(int[] nums, int target, int left, int right) {
        while (left <= right) {
            int middle = left + (right - left) / 2;

            if (nums[middle] == target) {
                return middle;
            } else if (nums[middle] < target) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        LC33 solution = new LC33();
        System.out.println("33. Search in Rotated Sorted Array [medium]");
        int[][] inputs = {
            {4, 5, 6, 7, 0, 1, 2},
            {4, 5, 6, 7, 0, 1, 2}
        };
        int[] targets = {0, 3};
        int[] answers = {4, -1};

        for (int i = 0; i < inputs.length; i++) {
            System.out.println("Example " + (i + 1));
            int[] input = inputs[i];
            int target = targets[i];
            int answer = answers[i];
            int output = solution.search(input, target);
            System.out.println("Input: numns = " + Arrays.toString(input) + ", target = " + target);
            System.out.println("Output: " + output);
            System.out.println("Answer: " + answer);
            System.out.println("Score: " + (answer == output));
            System.out.println();
        }
    }
}
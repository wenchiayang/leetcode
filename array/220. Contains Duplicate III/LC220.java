import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class LC220 {
    /**
     * Solution
     * Bucket
     * Put every number in a corresponding bucket
     * bucket_index = (number - min_mum) / (t + 1)
     * Reture true if we see two numbers in a same bucket
     * or the adjacent bucket has a number with diff less than t
     * https://www.youtube.com/watch?v=yc4hCFzNNQc
     * Time Complexity: O(n)
     * Space Complexity: O(bucket_size): worst case: O((max_num - min_num) / t))
     * Space Complexity: 優化: O(k)
     * @param nums
     * @param k
     * @param t
     * @return
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        // check corner cases
        if (nums == null || nums.length == 0 || k <= 0 || t < 0) {
            return false;
        }

        // find min
        int min = Integer.MAX_VALUE;
        for (int num : nums) {
            min = Math.min(num, min);
        }

        // Construct bucket
        Map<Long, Integer> bucket = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            long bucket_index = getBucketIndex(nums[i], min, t);

            // check left adjacent bucket
            Integer left_bucket = bucket.get(bucket_index - 1);
            if (left_bucket != null && isValidValue(nums[i], left_bucket, t)) {
                return true;
            }

            // check right adlacent bucket
            Integer right_bucket = bucket.get(bucket_index + 1);
            if (right_bucket != null && isValidValue(nums[i], right_bucket, t)) {
                return true;
            }

            // check current bucket
            Integer current_bucket = bucket.get(bucket_index);
            if (current_bucket != null && isValidValue(nums[i], current_bucket, t)) {
                return true;
            }

            // update bucket
            bucket.put(bucket_index, nums[i]);

            // remove index range that is grater that k
            if (i >= k) {
                long remove_index = getBucketIndex(nums[i - k], min, t);
                bucket.remove(remove_index);
            }
        }

        return false;
    }

    /**
     * Calculate bucket index
     * bucket_index = (number - min_mum) / (t + 1)
     * t + 1: avoid t = 0
     * @param value
     * @param min
     * @param t absolute difference between nums[i] and nums[j]
     * @return
     */
    private long getBucketIndex(int value, int min, int t) {
        long diff = Long.valueOf(t) + 1; // avoid t = 0
        return (Long.valueOf(value) - Long.valueOf(min)) / diff;
    }

    /**
     * Validate if absolute difference between 
     * nums[i] and nums[j] is at most t
     * @param target
     * @param bucket
     * @param t absolute difference between nums[i] and nums[j]
     * @return
     */
    private boolean isValidValue(int target, int bucket, int t) {
        long difference = Math.abs(Long.valueOf(target) - Long.valueOf(bucket));
        return difference <= Long.valueOf(t);
    }

    /**
     * Solution
     * TreeSet
     * Record numbers in a Binary Search Tree(TreeSet in Java)
     * Check if the difference between the new number and came in
     * and its cloest number in the BST is less than t
     * https://www.youtube.com/watch?v=yc4hCFzNNQc
     * Time Complexity: O(n * logk)
     * Space Complexity: O(k)
     * @param nums
     * @param k
     * @param t
     * @return
     */
    public boolean containsNearbyAlmostDuplicate2(int[] nums, int k, int t) {
        // check corner cases
        if (nums == null || nums.length == 0 || k <= 0 || t < 0) {
            return false;
        }

        // Binary Tree(TreeSet)
        TreeSet<Integer> bst = new TreeSet<>();

        for (int i = 0; i < nums.length; i++) {
            // find the smallest number larger than nums[i]
            Integer ceil = bst.ceiling(nums[i]);

            // use long here to avoid overflow
            // e.g. diff = 0 - Integer.MIN_VALUE
            if (ceil != null && Long.valueOf(ceil) - Long.valueOf(nums[i]) <= t) {
                return true;
            }

            // find the largest number smaller than nums[i]
            Integer floor = bst.floor(nums[i]);
            if (floor != null && Long.valueOf(nums[i]) - Long.valueOf(floor) <= t) {
                return true;
            }

            // if no match
            bst.add(nums[i]);

            // remove index range that is grater that k
            // only keep k elements on bst
            // because at most k
            if (i >= k) {
                bst.remove(nums[i - k]);
            }
        }

        return false;
    }

    /**
     * Solution
     * Naive Method(Time Limit Exceeded)
     * Try every number compare with its next k numbers
     * https://www.youtube.com/watch?v=yc4hCFzNNQc
     * Time Complexity: O(n * k)
     * Space Complexity: O(1)
     * @param nums
     * @param k
     * @param t
     * @return
     */
    public boolean containsNearbyAlmostDuplicate3(int[] nums, int k, int t) {
        // check corner cases
        if (nums == null || nums.length == 0 || k <= 0 || t < 0) {
            return false;
        }

        for (int i = 0; i < nums.length; i++) {
            // j - i <= k: make sure difference of index i and j is at most k
            for (int j = i + 1; j - i <= k && j < nums.length; j++) {
                // difference of nums[i] and nums[j] should less than t
                // use long here to avoid overflow
                // e.g. diff = 0 - Integer.MIN_VALUE
                long diff = Long.valueOf(nums[j]) - Long.valueOf(nums[i]);
                if (Math.abs(diff) <= t) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    public static void main(String[] args) {
        System.out.println("220. Contains Duplicate III [medium]");
        int[][] inputs = {
            {1, 2, 3, 1}, {1, 0, 1, 1}, {1, 5, 9, 1, 5, 9}
        };
        int[] ks = {3, 1, 2};
        int[] ts = {0, 2, 3};
        boolean[] answers = {true, true, false};

        LC220 solution = new LC220();
        for (int i = 0; i < inputs.length; i++) {
            int[] nums = inputs[i];
            int k = ks[i];
            int t = ts[i];
            boolean output = solution.containsNearbyAlmostDuplicate(nums, k, t);
            boolean answer = answers[i];
            boolean score = output == answer;
            System.out.println("Input: " + Arrays.toString(nums) + ", k = " + k + ", t = " + t);
            System.out.println("Output: " + output);
            System.out.println("Answer: " + answer);
            System.out.println("Score: " + score);
            System.out.println();
        }
    }
}
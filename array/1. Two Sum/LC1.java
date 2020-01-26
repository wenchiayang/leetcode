import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LC1 {
    /**
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        int[] pairs = new int[2];
        // (k,v):(target - num, index)
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            int remain = target - nums[i];

            if (map.containsKey(num)) {
                pairs[0] = Math.min(i, map.get(num));
                pairs[1] = Math.max(i, map.get(num));
                break;
            }

            map.put(remain, i);
        }

        
        return pairs;
    }
    
    public static void main(String[] args) {
        LC1 solutioln = new LC1();

        System.out.println("1. Two Sum [easy]");
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] output = solutioln.twoSum(nums, target);
        System.out.println("Given nums = " + Arrays.toString(nums) + ", target = " + target);
        System.out.println("Output = " + Arrays.toString(output));

    }
}
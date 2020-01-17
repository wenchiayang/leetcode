import java.util.Arrays;

/**
 * LC26
 */
public class LC26 {
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public int removeDuplicates(int[] nums) {
        // check edge cases
        if (nums == null || nums.length == 0) {
            return 0;
        } 

        int valid_index = 1;
        int check_value = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == check_value) {
                if (i + 1 < nums.length && nums[i + 1] != check_value) {
                    nums[valid_index++] = nums[i + 1];
                    check_value = nums[i + 1];
                }
            } else {
                check_value = nums[valid_index];
                valid_index++;
            }
        }

        return valid_index;
    }

    public int removeDuplicates2(int[] nums) {
        // check edge cases
        if (nums == null || nums.length == 0) { 
			return 0;
		}
        
		int slowerPointer = 0;
		
		for (int fasterPointer = 1; fasterPointer < nums.length; fasterPointer++) {
			if (nums[fasterPointer] != nums[slowerPointer]) {
				slowerPointer++;
				nums[slowerPointer] = nums[fasterPointer];
			}
		}
		
		return slowerPointer + 1;
    }

    public int removeDuplicates3(int[] nums) {
        // check edge cases
        if (nums == null || nums.length == 0) { 
			return 0;
		}
        
		int index = 1;
		
		for (int i = 0; i < nums.length - 1; i++) {
			if (nums[i] != nums[i + 1]) {
				nums[index++] = nums[i + 1];
			}
		}
		
		return index;
    }
    
    public static void main(String[] args) {
        LC26 solution = new LC26();
        int[][] inputs = {
            {1, 1, 2},
            {0, 0, 1, 1, 1, 2, 2, 3, 3, 4},
            {1, 2}, 
            {1, 2, 2}
        };
        
        for (int i = 0; i < inputs.length; i++) {
            System.out.println("Example: " + (i + 1));
            System.out.println("Given nums = " + Arrays.toString(inputs[i]));
            System.out.println("Output: " + solution.removeDuplicates(inputs[i]));
            System.out.println();
        }
    }
}
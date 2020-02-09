import java.util.Arrays;

/**
 * LC275
 */
public class LC275 {
    /**
     * Solution(Binary Search)
     * https://leetcode.com/problems/h-index-ii/solution/
     * Time Complexity: O(logn)
     * Space Complexity: O(1)
     * @param citations
     * @return
     */
    public int hIndex(int[] citations) {
        int n = citations.length;
        int left = 0, right = n - 1;
        
        while (left <= right) {
            int pivot = left + (right - left) / 2;
            int target = n - pivot;
            
            if (citations[pivot] == target) {
                return target;
            } else if (citations[pivot] > target) {
                right = pivot - 1;
            } else {
                left = pivot + 1;
            }
        }
        
        return n - left;
    }

    public static void main(String[] args) {
        LC275 solution = new LC275();
        int[][] inputs = {
            {3, 0, 6, 1, 5},
            {},
            {100},
            {0},
            {0, 1}
        };
        int[] answers = {
            3, 0, 1, 0, 1
        };
        
        System.out.println("275. H-Index II [medium]");
        for (int i = 0; i < inputs.length; i++) {
            System.out.println("Example: " + (i + 1));
            System.out.println("Input: " + Arrays.toString(inputs[i]));
            int output = solution.hIndex(inputs[i]);
            System.out.println("Output: " + output);
            System.out.println("Answer: " + answers[i]);
            System.out.println("Score: " + (output == answers[i]));
            System.out.println();
        }
    }
}
import java.util.Arrays;

/**
 * LC274
 */
public class LC274 {
    /**
     * Solution
     * https://zhuhan0.blogspot.com/2017/08/leetcode-274-h-index.html
     * https://www.hrwhisper.me/leetcode-h-index-h-index-ii/
     * Time Complexity: O(nlogn)
     * Space Complexity: O(1)
     * @param citations
     * @return
     */
    public int hIndex(int[] citations) {
        // check edge cases
        if (citations == null || citations.length == 0) {
            return 0;
        }
        
        Arrays.sort(citations);
        
        // start from last index -> act like descending
        int n = citations.length;
        for (int i = n - 1; i >=0; i--) {
            int h_index = n - i;
            if (citations[i] < h_index) {
                return h_index - 1;
            }
        }
        
        return n;
    }

    // private void reverse(int[] input) {
    //     int last = input.length - 1;
    //     int middle = input.length / 2;

    //     for (int i = 0; i <= middle; i++) {
    //         int temp = input[i];
    //         input[i] = input[last - i];
    //         input[last - i] = temp;
    //     }
    // }

    public static void main(String[] args) {
        LC274 solution = new LC274();
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
        
        System.out.println("274. H-Index [medium]");
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
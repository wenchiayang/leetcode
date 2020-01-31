import java.util.Arrays;

public class LC81 {
    /**
     * Solution
     * 
     * 
     * Time Complexity: O()
     * Space Complexity: O()
     * @param nums
     * @param target
     * @return
     */
    public boolean search(int[] nums, int target) {
        return true;
    }

    public static void main(String[] args) {
        LC81 solution = new LC81();
        System.out.println("81. Search in Rotated Sorted Array II [medium]");
        int[][] inputs = {
            {2, 5, 6, 0, 0, 1, 2},
            {2, 5, 6, 0, 0, 1, 2}
        };
        int[] targets = {0, 3};
        boolean[] answers = {true, false};

        for (int i = 0; i < inputs.length; i++) {
            System.out.println("Example " + (i + 1));
            int[] input = inputs[i];
            int target = targets[i];
            boolean answer = answers[i];
            boolean output = solution.search(input, target);
            System.out.println("Input: numns = " + Arrays.toString(input) + ", target = " + target);
            System.out.println("Output: " + output);
            System.out.println("Answer: " + answer);
            System.out.println("Score: " + (answer == output));
            System.out.println();
        }
    }
}
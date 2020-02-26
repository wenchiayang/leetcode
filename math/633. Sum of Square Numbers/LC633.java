
public class LC633 {
    /**
     * Solution
     * https://www.youtube.com/watch?v=2tiZMMTt-CQ
     * Time Complexity: O(sqrt(c)) = O(2^16) = O(65536) = O(1)
     * Space Complexity: O(1)
     */
    public boolean judgeSquareSum(int c) {
        int a = 0;
        while (a <= Math.sqrt(c)) {
            int b = (int) Math.round(Math.sqrt(c - a * a));
            if (a * a + b * b == c) {
                return true;
            }

            a++;
        }

        return false;
    }
    
    public static void main(String[] args) {
        System.out.println("633. Sum of Square Numbers [easy]");
        LC633 solution = new LC633();

        int[] nums = {2, 5, 3, 4};
        boolean[] answers = {true, true, false, true};

        for (int i = 0; i < answers.length; i++) {
            System.out.println("Example " + (i + 1) + ":");
            int c = nums[i];
            boolean output = solution.judgeSquareSum(c);
            boolean answer = answers[i];
            boolean score = output == answer;

            System.out.println("Input: " + c);
            System.out.println("Output: " + output);
            System.out.println("Answer: " + answer);
            System.out.println("Score: " + score);
            System.out.println();
        }
    }
}

public class LC172 {
    /**
     * Solution
     * https://www.youtube.com/watch?v=T3lhjrX2z9k
     * trailing 0 -> find 10^n -> find (2 * 5)^n
     * 2 is easy to capture
     * # of 5 -> # of 10 -> # of 0 in the trailing
     * Time Complexity: O(logn)
     * Space Complexity: O(logn)
     * @param n
     * @return
     */
    public int trailingZeroes(int n) {
        int zeros = 0;

        while (n != 0) {
            zeros += n / 5;
            n /= 5;
        }
        
        return zeros;
    }

    /**
     * Solution
     * Recursion
     * https://zxi.mytechroad.com/blog/math/leetcode-172-factorial-trailing-zeroes/
     * Time Complexity: O(logn)
     * Space Complexity: O(logn)
     * @param n
     * @return
     */
    public int trailingZeroes2(int n) {
        return n < 5 ? 0 : n / 5 + trailingZeroes2(n / 5) ;
    }
    
    public static void main(String[] args) {
        System.out.println("172. Factorial Trailing Zeroes [easy]");
        LC172 solution = new LC172();

        int[] inputs = {3, 5, 1808548329};
        int[] answers = {0, 1, 452137076};

        for (int i = 0; i < answers.length; i++) {
            System.out.println("Example " + (i + 1) + ":");
            int n = inputs[i];
            int output = solution.trailingZeroes(n);
            int answer = answers[i];
            boolean score = output == answer;

            System.out.println("Input: " + n);
            System.out.println("Output: " + output);
            System.out.println("Answer: " + answer);
            System.out.println("Score: " + score);
            System.out.println();
        }
    }
}
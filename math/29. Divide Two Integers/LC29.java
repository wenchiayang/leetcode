public class LC29 {
    /**
     * Solution
     * https://leetcode.com/problems/divide-two-integers/discuss/13397/Clean-Java-solution-with-some-comment.
     * Time Complexity: O(logn)
     * Space Complexity: O(logn)
     * @param dividend
     * @param divisor
     * @return
     */
    public int divide(int dividend, int divisor) {
        // think about corner cases
        // 1. + -
        // 2. 越界(over flow)
        // 3. = 0 -> 3/5
        // 4. normal case

        // overflow
        if (dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE;

        // sign
        boolean sign = ((dividend < 0) && (divisor < 0)) || ((dividend > 0) && (divisor > 0));
        
        // calculate
        // use negative number to avoid overflow
        int quotient = div(-Math.abs(dividend), -Math.abs(divisor));
        
        return sign ? quotient : -quotient;
    }

    private int div(int dividend, int divisor) {
        if (dividend > divisor) return 0;
        
        int sum = divisor, q = 1;
        while (dividend <= sum + sum && sum + sum < sum) {
            sum += sum;
            q += q;
        }

        return q + div(dividend - sum, divisor);
    }
    
    public static void main(String[] args) {
        LC29 solution = new LC29();
        System.out.println("29. Divide Two Integers [easy]");
        int[] dividends = {10, 7, 0, 1, -1, Integer.MIN_VALUE};
        int[] divisors = {3, -3, 1, 1, 1, -1};
        int[] answers = {3, -2, 0, 1, -1, Integer.MAX_VALUE};

        for (int i = 0; i < dividends.length; i++) {
            System.out.println("Example " + (i + 1) + ":");
            int dividend = dividends[i];
            int divisor = divisors[i];
            int answer = answers[i];
            int output = solution.divide(dividend, divisor);
            System.out.println("Input: dividend = " + dividend + ", divisor = " + divisor);
            System.out.println("Output: " + output);
            System.out.println("Answer: " + answer);
            System.out.println("Score: " + (output == answer));
            System.out.println();
        }
    }
}
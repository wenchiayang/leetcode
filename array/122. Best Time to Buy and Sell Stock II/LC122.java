import java.util.Arrays;

public class LC122 {
    /**
     * Solution
     * One pass
     * https://www.youtube.com/watch?v=blUwDD6JYaE
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        // check corner cases
        if (prices == null || prices.length == 0) {
            return 0;
        }

        int profit = 0;

        for (int i = 0; i < prices.length - 1; i++) {
            // everytime we have positive profit(next > current)
            // we update profit
            if (prices[i + 1] > prices[i]) {
                profit += prices[i + 1] - prices[i];
            }
        }

        return profit;
    }

    public static void main(String[] args) {
        System.out.println("122. Best Time to Buy and Sell Stock II [easy]");
        int[][] inputs = {
            {7, 1, 5, 3, 6, 4},
            {1, 2, 3, 4, 5}
        };
        int[] answers = {7, 4};
        LC122 solution = new LC122();

        for (int i = 0; i < inputs.length; i++) {
            int[] prices = inputs[i];
            int answer = answers[i];
            int output = solution.maxProfit(prices);
            boolean score = answer == output;

            System.out.println("Example " + (i + 1) + ":");
            System.out.println("Input: " + Arrays.toString(prices));
            System.out.println("Output: " + output);
            System.out.println("Answer: " + answer);
            System.out.println("Score: " + score);
            System.out.println();
        }
    }
}
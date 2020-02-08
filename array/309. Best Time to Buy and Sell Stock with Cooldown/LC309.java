import java.util.Arrays;

public class LC309 {
    /**
     * Solution
     * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/discuss/75931/Easiest-JAVA-solution-with-explanations
     * 1. Define States
     * To represent the decision at index i:
     * - buy[i]: Max profit till index i. The series of transaction is ending with a buy.
     * - sell[i]: Max profit till index i. The series of transaction is ending with a sell.
     * To clarify:
     * - Till index i, the buy / sell action must happen and must be the last action. It may not happen at index i. It may happen at i - 1, i - 2, ... 0.
     * - In the end n - 1, return sell[n - 1]. Apparently we cannot finally end up with a buy. In that case, we would rather take a rest at n - 1.
     * - For special case no transaction at all, classify it as sell[i], so that in the end, we can still return sell[n - 1].
     * 2. Define Recursion
     * - buy[i]: To make a decision whether to buy at i, we either take a rest, by just using the old decision at i - 1, or sell at/before i - 2, then buy at i, We cannot sell at i - 1, then buy at i, because of cooldown.
     * - sell[i]: To make a decision whether to sell at i, we either take a rest, by just using the old decision at i - 1, or buy at/before i - 1, then sell at i.
     * So we get the following formula:
     * - buy[i] = Math.max(buy[i - 1], sell[i - 2] - prices[i]);
     * - sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]);
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        // check edge cases
        if (prices == null || prices.length <= 1) {
            return 0;
        }

        int len = prices.length;
        int[] sell = new int[len];
        int[] buy = new int[len];

        // base case
        buy[0] = -prices[0];
        sell[0] = 0;

        for (int i = 1; i < len; i++) {
            if (i == 1) {
                buy[i] = Math.max(buy[i - 1], -prices[i]);
            } else {
                buy[i] = Math.max(buy[i - 1], sell[i - 2] - prices[i]);
            }

            sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]);
        }

        // In the end n - 1, return sell[n - 1]. 
        // Apparently we cannot finally end up with a buy. 
        // In that case, we would rather take a rest at n - 1.
        return sell[len - 1];
    }


    /**
     * Solution
     * Dynamic Programming
     * https://www.youtube.com/watch?v=Ggzbo9eVrLU
     * 1. Define States
     * holds[i]:   The max profit of the ith day that we hold the stock
     * unholds[i]: The max profit of the ith day that we don't hold the stock
     * 2. Target: The max profit of the last day that we don't hold the stock
     * unholds[len(prices) - 1]
     * 3. Base Case:
     * holds[0] = -prices[0]
     * holds[1] = max(-prices[1], -prices[0])
     * unholds[0] = 0
     * 4. State Transformation
     * holds[i] pick max value of the following cases
     * (a) buy at ith day: unholds[i - 2] - prices[i]
     * (b) not buy at ith day: holds[i - 1]
     * unholds[i] pick max value of the following cases
     * (a) buy at ith day: holds[i - 1] + prices[i]
     * (b) not buy at ith day: unholds[i - 1]
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
        // check corner cases
        if (prices == null || prices.length == 0) {
            return 0;
        }

        int length = prices.length;
        int[] holds = new int[length];
        int[] unholds = new int[length];

        // Base Case 1
        // if we hold the stock at day 0
        // Max profit of holding the stock: -prices[0]
        holds[0] = -prices[0];
        unholds[0] = 0;

        // State Transformation
        // calculate holds and unholds
        for (int i = 1; i < length; i++) {
            if (i == 1) {
                // Base Case
                // if we hold the stock at day 1
                // calculate max profit of -prices[0] and -prices[1]
                // holds[0] == -prices[0]
                holds[i] = Math.max(holds[i - 1], -prices[1]);
            } else {
                // calulate max profit of the end of the ith day if we hold the stock
                // there are 2 cases
                // 1. we bought the stock at the end of the ith day
                // we minus prices[i] first
                // and our previous state: unholds[i - 2]
                // because before buying the stock, the previous day should be cooled down
                // and the previous 2 days must not be the day that bought the stock
                // so -> the previous 2 days was not buying the stock
                // so the profit from that day was: unholds[i - 2]
                // 2. we didn't buy the stock at the end of the ith day
                // state is from the previous hold day
                holds[i] = Math.max(holds[i - 1], unholds[i - 2] - prices[i]);
            }

            // calulate max profit of the end of the ith day if we didn't hold the stock
            // there are 2 cases
            // 1. we sell the stock at the end of the ith day
            // so we must calculate from the previous day that the max profit that we hold the stock
            // 2. we didn't sell the stock at the end of the ith day
            // state must come from the previous day
            unholds[i] = Math.max(unholds[i - 1], holds[i - 1] + prices[i]);
        }

        // The max profit of the last day that we don't hold the stock
        return unholds[length - 1];
    }

    /**
     * Naive Solution(TLE)
     * For every day, you can have 3 options: buy, sell and cool down.
     * So you can use brute force to list all profit of all transactions.
     * Return maximum profit
     * Time Complexity: O(3^n)
     * Space Complexity: O(n)
     */
    
    public static void main(String[] args) {
        System.out.println("309. Best Time to Buy and Sell Stock with Cooldown [medium]");
        int[][] inputs = {
            {1, 2, 3, 0, 2}
        };
        int[] answers = {3};
        LC309 solution = new LC309();

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
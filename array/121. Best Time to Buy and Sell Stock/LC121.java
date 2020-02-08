import java.util.Arrays;

public class LC121 {
    /**
     * Solution
     * One pass
     * https://www.youtube.com/watch?v=mj7N8pLCJ6w
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

        int max = 0;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < min) {
                // update current min price
                min = prices[i];
            } else {
                // calculate profit and set max profit
                int profit = prices[i] - min;
                max = Math.max(max, profit);
            }
        }

        return max;
    }

    /**
     * Brute Force
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
        // check corner cases
        if (prices == null || prices.length == 0) {
            return 0;
        }

        int max = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                // prices[j]: later - prices[i]: previous
                int profit = prices[j] - prices[i];
                max = Math.max(profit, max);
            }
        }

        return max;
    }
    
    public static void main(String[] args) {
        System.out.println("121. Best Time to Buy and Sell Stock [easy]");
        int[][] inputs = {
            {7, 1, 5, 3, 6, 4},
            {7, 6, 4, 3, 1},
            {1, 2}
        };
        int[] answers = {5, 0, 1};
        LC121 solution = new LC121();

        for (int i = 0; i < inputs.length; i++) {
            int[] prices = inputs[i];
            int answer = answers[i];
            int output = solution.maxProfit(prices);
            boolean score = answer == output;

            System.out.println("Input: " + Arrays.toString(prices));
            System.out.println("Output: " + output);
            System.out.println("Answer: " + answer);
            System.out.println("Score: " + score);
            System.out.println();
        }
    }
}
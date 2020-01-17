import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

/**
 * LC134
 */
public class LC134 {
    // Solution
    // https://www.youtube.com/watch?v=nTKdYm_5-ZY&list=PLupD_xFct8mETlGFlLVrwbLwcxczbgWRM&index=8&t=0s
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int sum = 0;
        int diff = 0;
        int start = 0;

        for (int i = 0; i < gas.length; i++) {
            sum = sum + gas[i] - cost[i];

            if (sum < 0) {
                start = i + 1; // changing start point
                diff += sum; // storing the negative value
                sum = 0; // starting again from the new station
            }
        }

        return (sum + diff) >= 0 ? start : -1;
    }

    // Brute Force
    // Time Complexity: O(n^2)
    // Space Complexity: O(n)
    public int canCompleteCircuit2(int[] gas, int[] cost) {
        // check edge cases
        if (getSum(gas) < getSum(cost)) {
            return -1;
        }

        // find possible valid indice
        Queue<Integer> validIndices = new PriorityQueue<>();
        for (int i = 0; i < gas.length; i++) {
            if (gas[i] >= cost[i]) {
                validIndices.add(i);
            }
        }
 
        int startIndex = -1;

        while (!validIndices.isEmpty()) {
            int validIndex = validIndices.poll();
            int remainingGas = gas[validIndex];
            boolean valid = true;

            // System.out.println("validIndex: " + validIndex);
            // System.out.println("Origin gas: " + remainingGas);
            // System.out.println();
            // calculate
            for (int i = 0; i < gas.length; i++) {
                int costIndex = validIndex + i;
                int gasIndex = validIndex + i + 1;

                if (costIndex >= gas.length) {
                    costIndex -= gas.length;
                }

                if (gasIndex >= gas.length) {
                    gasIndex -= gas.length;
                }

                remainingGas -= cost[costIndex];

                if (remainingGas < 0) {
                    valid = false;
                    break;
                }
                
                remainingGas += gas[gasIndex];
                // System.out.println("-cost: " + cost[costIndex]);
                // System.out.println("+gas: " + gas[gasIndex]);
                // System.out.println("remaining gas: " + remainingGas);
            }

            if (valid) {
                // add gas at validIndex twice, need to adjust
                remainingGas = remainingGas - gas[validIndex];
                // System.out.println("-gas: " + gas[validIndex]);
                // System.out.println("fianl gas: " + remainingGas);

                if (remainingGas >= 0) {
                    startIndex = validIndex;
                }
            }

            // System.out.println("start index: " + startIndex);
            // System.out.println();
        }

        return startIndex;
    }

    private int getSum(int[] input) {
        int sum = 0;

        for (int i : input) {
            sum += i;
        }

        return sum;
    }

    public static void main(String[] args) {
        LC134 solution = new LC134();
        int[][] gases = {
            {1, 2, 3, 4, 5},
            {2, 3, 4},
            {5, 1, 2, 3, 4}
        };
        int[][] costs = {
            {3, 4, 5, 1, 2},
            {3, 4, 3},
            {4, 4, 1, 5, 1}
        };
        int[] answers = {3, -1, 4};

        System.out.println("134. Gas Station [medium]");
        for (int i = 0; i < gases.length; i++) {
            System.out.println("Example: " + (i + 1));
            System.out.println("gas : " + Arrays.toString(gases[i]));
            System.out.println("cost: " + Arrays.toString(costs[i]));
            int output = solution.canCompleteCircuit(gases[i], costs[i]);
            System.out.println("Output: " + output);
            System.out.println("Answer: " + answers[i]);
            System.out.println("Score: " + (output == answers[i]));
            System.out.println();
        }
    }
}
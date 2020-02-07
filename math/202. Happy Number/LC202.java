import java.util.HashSet;
import java.util.Set;

public class LC202 {
    /**
     * Solution
     * https://www.youtube.com/watch?v=gW4hSbRoQoY
     * Time Complexity: O(logn)
     * Space Complexity: O(logn)
     */
    public boolean isHappy(int n) {
        Set<Integer> seen = new HashSet<>();
        
        while (n != 1) {
            int curr = n;
            int sum = 0;

            // add by square
            while (curr != 0) {
                sum += (curr % 10) * (curr % 10);
                curr /= 10;
            }

            // we've already seen this, return false
            if (seen.contains(sum)) {
                return false;
            }

            seen.add(sum);
            n = sum;
        }

        return true;
    }

    public static void main(String[] args) {
        LC202 solution = new LC202();
        System.out.println("202. Happy Number [easy]");
        int[] inputs = {19, 2};
        boolean[] answers = {true, false};

        for (int i = 0; i < inputs.length; i++) {
            int input = inputs[i];
            boolean answer = answers[i];
            boolean output = solution.isHappy(input);
            System.out.println("Input: " + input);
            System.out.println("Output: " + output);
            System.out.println("Answer: " + answer);
            System.out.println("Score: " + (answer == output));
            System.out.println();
        }
    }
}
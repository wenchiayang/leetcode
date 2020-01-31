
public class LC69 {

    /**
     * Solution
     * Binary Search
     * Time Complexity: O(logn)
     * Space Complexity: O(1)
     * @param x
     * @return
     */
    public int mySqrt(int x) {
        if (x < 2) return x;

        int left = 1;
        int right = x / 2;

        while (left <= right) {
            int middle = left + (right - left) / 2;
            if (middle > x / middle) { // avoid overflow
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }

        return left - 1;
    }

    /**
     * My Brute Force
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * @param x
     * @return
     */
    public int mySqrt2(int x) {
        if (x == 1) return 1;
        
        for (int i = 0; i <= x / 2 + 1; i++) {
            int diff = x - (i * i);
            
            if (diff == 0) {
                return i;
            } else if (diff < 0) {
                return i - 1;
            }
        }
        
        return 0;
    }

    public int mySqrt3(int x) {
        return (int) Math.sqrt((double) x);
    }
    
    public static void main(String[] args) {
        LC69 solution = new LC69();
        System.out.println("69. Sqrt(x) [easy]");
        int[] inputs = {4, 8, 1, 2, 5};
        int[] answers = {2, 2, 1, 1, 2};

        for (int i = 0; i < inputs.length; i++) {
            System.err.println("Example " + (i + 1) + ":");
            int input = inputs[i];
            int answer = answers[i];
            int output = solution.mySqrt(input);
            System.out.println("Input: " + input);
            System.out.println("Output: " + output);
            System.out.println("Answer: " + answer);
            System.out.println("Score: " + (output == answer));
            System.out.println();
        }
    }
}
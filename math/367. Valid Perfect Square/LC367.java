
public class LC367 {
    /**
     * Solution
     * Binary Search
     * https://www.youtube.com/watch?v=-oqZCmhJ2Zs
     * Time Complexity: O(logn)
     * Space Complexity: O(1)
     * @param num
     * @return
     */
    public boolean isPerfectSquare(int num) {
        if (num < 1) return false;
        if (num == 1) return true;

        long left = 1, right = num / 2;

        while (left <= right) {
            long middle = left + (right - left) / 2;
            long square = middle * middle;
            if (square == num) {
                return true;
            } else if (square < num) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }

        return false;
    }
    
    /**
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * @param num
     * @return
     */
    public boolean isPerfectSquare2(int num) {
        if (num < 1) return false;
        if (num == 1) return true;

        for (int i = 2; i <= num / 2; i++) {
            if (i * i == num) {
                return true;
            }
        }

        return false;
    }
    
    public static void main(String[] args) {
        System.out.println("367. Valid Perfect Square [easy]");
        LC367 solution = new LC367();

        int[] nums = {808201, 16, 14};
        boolean[] answers = {true, true, false};

        for (int i = 0; i < answers.length; i++) {
            System.out.println("Example " + (i + 1) + ":");
            int num = nums[i];
            boolean output = solution.isPerfectSquare(num);
            boolean answer = answers[i];
            boolean score = output == answer;

            System.out.println("Input: " + num);
            System.out.println("Output: " + output);
            System.out.println("Answer: " + answer);
            System.out.println("Score: " + score);
            System.out.println();
        }
    }
}
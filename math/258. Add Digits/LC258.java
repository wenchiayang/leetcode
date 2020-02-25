
public class LC258 {
    /**
     * Solution
     * https://leetcode.com/problems/add-digits/discuss/68726/Java-one-line-simple-answer
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * @param num
     * @return
     */
    public int addDigits(int num) {
        // For number that from 0 to 9, the answer is themselves
        // For number that is divisible by 9, the answer is 9
        // Otherwise, the answer is the reminder after divided by 9
        if (num < 10) {
            return num;
        } else if (num % 9 == 0) {
            return 9;
        } else {
            return num % 9;
        }
    }

    /**
     * Recursion
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * @param num
     * @return
     */
    public int addDigits2(int num) {
        if (num < 10) {
            return num;
        }

        int sum = 0;

        while (num != 0) {
            sum += num % 10;
            num /= 10;
        }

        return addDigits(sum);
    }

    public static void main(String[] args) {
        System.out.println("258. Add Digits [easy]");
        LC258 solution = new LC258();

        int[] nums = {38, 98};
        int[] answers = {2, 8};

        for (int i = 0; i < answers.length; i++) {
            System.out.println("Example " + (i + 1) + ":");
            int num = nums[i];
            int output = solution.addDigits(num);
            int answer = answers[i];
            boolean score = output == answer;

            System.out.println("Input: " + num);
            System.out.println("Output: " + output);
            System.out.println("Answer: " + answer);
            System.out.println("Score: " + score);
            System.out.println();
        }
    }
}
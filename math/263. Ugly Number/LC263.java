
public class LC263 {
    /**
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * @param num
     * @return
     */
    public boolean isUgly(int num) {
        if (num < 1) return false;
        if (num == 1) return true;

        while (num != 1) {
            if (num % 2 == 0) {
                num /= 2;
            } else if (num % 3 == 0) {
                num /= 3;
            } else if (num % 5 == 0) {
                num /= 5;
            } else {
                return false;
            }
        }

        return true;
    }
    
    public static void main(String[] args) {
        System.out.println("263. Ugly Number [easy]");
        LC263 solution = new LC263();

        int[] nums = {6, 8, 14};
        boolean[] answers = {true, true, false};

        for (int i = 0; i < answers.length; i++) {
            System.out.println("Example " + (i + 1) + ":");
            int num = nums[i];
            boolean output = solution.isUgly(num);
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
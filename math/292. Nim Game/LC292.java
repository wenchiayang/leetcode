
public class LC292 {
    /**
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * @param n
     * @return
     */
    public boolean canWinNim(int n) {
        return n % 4 != 0;
    }
    
    public static void main(String[] args) {
        System.out.println("292. Nim Game [easy]");
        LC292 solution = new LC292();

        int[] inputs = {4};
        boolean[] answers = {false};

        for (int i = 0; i < answers.length; i++) {
            System.out.println("Example " + (i + 1) + ":");
            int n = inputs[i];
            boolean output = solution.canWinNim(n);
            boolean answer = answers[i];
            boolean score = output == answer;

            System.out.println("Input: " + n);
            System.out.println("Output: " + output);
            System.out.println("Answer: " + answer);
            System.out.println("Score: " + score);
            System.out.println();
        }
    }
}
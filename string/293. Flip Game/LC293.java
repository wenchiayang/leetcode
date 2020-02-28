import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class LC293 {
    /**
     * Solution
     * https://medium.com/leetcode-%E6%BC%94%E7%AE%97%E6%B3%95%E6%95%99%E5%AD%B8/012-leetcode-293-%E6%BC%94%E7%AE%97%E6%B3%95-flip-game-%E7%BF%BB%E8%BD%89%E9%81%8A%E6%88%B2-5360129b99a0
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * @param s
     * @return
     */
    public List<String> generatePossibleNextMoves(String s) {
        List<String> moves = new ArrayList<>();

        for (int i = 1; i < s.length(); i++) {
            // check if current and previous chars are same
            if (s.charAt(i) == '+' && s.charAt(i - 1) == '+') {
                // String move = s.substring(0, i - 1) + "--" + s.substring(i + 1, s.length());
                String move = flip(s, i - 1, i);
                moves.add(move);
            }
        }

        return moves;
    }

    private String flip(String s, int index1, int index2) {
        char[] array = s.toCharArray();
        for (int i = 0; i < array.length; i++) {
            if (i == index1 || i == index2) {
                array[i] = '-';
            }
        }
        return new String(array);
    }

    public static void main(String[] args) {
        System.out.println("293. Flip Game [easy]");
        LC293 solution = new LC293();
        
        String[] ss = {"++++", "--", "---+++-+++-+"};
        String[][] answers = {
            {"--++", "+--+", "++--"},
            {},
            {"-----+-+++-+","---+---+++-+","---+++---+-+","---+++-+---+"}
        };

        for (int i = 0; i < answers.length; i++) {
            System.out.println("Example " + (i + 1) + ":");
            String s = ss[i];
            List<String> output = solution.generatePossibleNextMoves(s);
            String[] answer = answers[i];

            System.out.println("Input: s = " + s);
            System.out.println("Output: " + Arrays.toString(output.toArray()));
            System.out.println("Answer: " + Arrays.toString(answer));
            System.out.println();
        }
    }
}
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class LC22 {
    /**
     * Solution
     * Time Complexity: O(2^n)
     * Space Complexity: O(n)
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        List<String> parenthesis = new ArrayList<>();
        // check corner case
        if (n == 0) return parenthesis;
        backtrack(parenthesis, "", n, n);
        return parenthesis;
    }

    private void backtrack(List<String> parenthesis, String content, int open_remain, int close_remain) {
        // check invalid case:
        // count of remaining open must to be less than or equal to the count of remaining close
        // (means: count of open >= close)
        if (open_remain > close_remain) {
            return;
        }

        // case to add generated result to the final result
        if (open_remain == 0 && close_remain == 0) {
            parenthesis.add(content);
        }

        // cases to generate open or close parenthesis
        if (open_remain > 0) {
            backtrack(parenthesis, content + "(", open_remain - 1, close_remain);
        }

        if (close_remain > 0) {
            backtrack(parenthesis, content + ")", open_remain, close_remain - 1);
        }
    }

    public static void main(String[] args) {
        LC22 solution = new LC22();
        System.out.println("22. Generate Parentheses [medium]");
        int n = 3;
        String[] answers = {
            "((()))",
            "(()())",
            "(())()",
            "()(())",
            "()()()"
        };

        System.out.println("given: n = " + n);
        List<String> output = solution.generateParenthesis(n);
        System.out.println("Output: " + Arrays.toString(output.toArray()));
        System.out.println("Answer: " + Arrays.toString(answers));
        System.out.println();
    }
}
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

/**
 * LC118
 */
public class LC118 {
    // Solution: https://www.youtube.com/watch?v=gq4t3cwMQbs
    // Time Complexity: O(numRows^2)
    // Space Complexity: O(numRows^2)
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> triangle = new ArrayList<>();
        
        // check edge cases
        if (numRows == 0) {
            return triangle;
        }
        
        for (int i = 0; i < numRows; i++) {
            List<Integer> row = new ArrayList<>();
            
            for (int j = 0; j <= i; j++) {
                // first and last index -> 1
                if (j == 0 || j == i) {
                    row.add(1);
                } else { // middle: sum of previous j - 1 and previous j
                    List<Integer> previous = triangle.get(i - 1);
                    row.add(previous.get(j - 1) + previous.get(j));
                }
            }
            
            triangle.add(row);
        }
        
        return triangle;
    }

    public static void main(String[] args) {
        LC118 solution = new LC118();
        int[] inputs = {1, 5};
        int[][][] answers = {
            {{1}},
            {{1}, {1, 1}, {1, 2, 1}, {1, 3, 3, 1}, {1, 4, 6, 4, 1}}
        };

        System.out.println("118. Pascal's Triangle [easy]");
        for (int i = 0; i < inputs.length; i++) {
            System.out.println("Example: " + (i + 1));
            System.out.println("Input: " + inputs[i]);
            List<List<Integer>> output = solution.generate(inputs[i]);
            int[][] answer = answers[i];
            System.out.println("Output: " + Arrays.deepToString(output.toArray()));
            System.out.println("Answer: " + Arrays.deepToString(answer));
            System.out.println();
        }
    }
}
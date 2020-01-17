import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

/**
 * LC119
 */
public class LC119 {
    // Solution: https://www.youtube.com/watch?v=PKiV5HhnfDw
    // Time Complexity: O(k)
    // Space Complexity: O(k)
    public List<Integer> getRow(int rowIndex) {
        List<Integer> row = new ArrayList<>();

        for (int i = 0; i <= rowIndex; i++) {
            row.add(1);

            // System.out.println("i: " + i);
            // System.out.println("row: " + Arrays.deepToString(row.toArray()));
            // System.out.println();

            for (int j = i - 1; j > 0; j--) {
                row.set(j, row.get(j) + row.get(j - 1));

                // System.out.println("(i,j): (" + i + "," + j + ")");
                // System.out.println("row: " + Arrays.deepToString(row.toArray()));
                // System.out.println();
            }
        }

        return row;
    }
    
    // Time Complexity: O(rowIndex^2)
    // Space Complexity: O(rowIndex^2)
    public List<Integer> getRow2(int rowIndex) {
        int  numRows = rowIndex + 1;
        List<List<Integer> > allRows = new ArrayList<List<Integer>>();

        for (int i = 0; i < numRows; i++) {
            List<Integer> rows = new ArrayList<>();

            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    rows.add(j, 1);
                } else {
                    rows.add(j, allRows.get(i - 1).get(j - 1) + allRows.get(i - 1).get(j));
                }
            }

            allRows.add(rows);
        }
        
        return allRows.get(rowIndex);
    }

    public static void main(String[] args) {
        LC119 solution = new LC119();
        int[] inputs = {1, 3};
        int[][] answers = {
            {1, 1},
            {1, 3, 3, 1}
        };

        System.out.println("119. Pascal's Triangle II [easy]");
        for (int i = 0; i < inputs.length; i++) {
            System.out.println("Example: " + (i + 1));
            System.out.println("Input: " + inputs[i]);
            List<Integer> output = solution.getRow(inputs[i]);
            int[] answer = answers[i];
            System.out.println("Output: " + Arrays.deepToString(output.toArray()));
            System.out.println("Answer: " + Arrays.toString(answer));
            System.out.println();
        }
    }
}
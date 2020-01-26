import java.util.Arrays;

/**
 * LC463
 */
public class LC463 {
    // Time Complexity: O(m*n), where (m,n) is the (row,column) of the grid
    // Space Complexity: O(1)
    public int islandPerimeter(int[][] grid) {
        // check edge cases
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int perimeter = 0;
        
        for (int row = 0; row < grid.length; row++) {
            for (int column = 0; column < grid[0].length; column++) {
                if (grid[row][column] == 1) {
                    // perimeter += findValidSide(grid, row, column);
                    perimeter += adjust(grid, row, column);
                }
            }
        }
        
        return perimeter;
    }

    // Simple Solution: https://www.youtube.com/watch?v=FkjFlNtTzc8
    private int adjust(int[][] grid, int row, int column) {
        int side = 4;

        if (row > 0 && grid[row - 1][column] == 1) {
            side -= 2;
        }

        if (column > 0 && grid[row][column - 1] == 1) {
            side -= 2;
        }

        return side;
    }
    
    // My Solution
    private int findValidSide(int[][] grid, int row, int column) {
        int sides = 0;
        
        if (column - 1 == -1) {
            sides += 1;
        } else {
            if (grid[row][column - 1] == 0) { // left
                sides += 1;
            }
        }
        
        if (row - 1 == -1) {
            sides += 1;
        } else {
            if (grid[row - 1][column] == 0) { // up
                sides += 1;
            }    
        }
        
        if (column + 1 == grid[0].length) {
              sides += 1;
        } else {
            if (grid[row][column + 1] == 0) {  // right
                sides += 1;
            }  
        }
        
        if (row + 1 == grid.length) {
            sides += 1;
        } else {
            if (grid[row + 1][column] == 0) { // down
                sides += 1;
            }    
        }
        
        return sides;
    }
    
    public static void main(String[] args) {
        LC463 solution = new LC463();
        
        int[][][] inputs = {
            {},
            {{1}},
            {{0, 1, 0, 0}, {1, 1, 1, 0}, {0, 1, 0, 0}, {1, 1, 0, 0}},
            {{1, 0}, {0, 0}}
        };

        for (int i = 0; i < inputs.length; i++) {
            System.out.println("Example " + (i + 1));
            System.out.println("Input: " + Arrays.deepToString(inputs[i]));
            System.out.println("Output: " + solution.islandPerimeter(inputs[i]));   
            System.out.println();
        }
    }
}
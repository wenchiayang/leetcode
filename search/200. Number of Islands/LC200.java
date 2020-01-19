import java.util.LinkedList;
import java.util.Queue;

/**
 * LC200
 */
public class LC200 {
    /**
     * Solution(DFS)
     * https://www.youtube.com/watch?v=hhBiPyr1Byo
     * Time Complexity: O(m * n), where m, n are rows and columns
     * Space Complexity: worst case O(M×N) in case that the grid map is filled with lands where DFS goes by M×N deep.
     * @param grid
     * @return
     */
    final static int[][] directions = {
        {-1, 0}, {1, 0}, {0, -1}, {0, 1} // left, rigth, up, down
    };
    
    // Assumption
    public int numIslands2(char[][] grid) {
        // check corner cases
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int islands = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    islands += dfs(grid, i, j, grid.length, grid[i].length);
                }
            }
        }

        return islands;
    }

    private int dfs(char[][] grid, int x, int y, int rows, int columns) {
        // base case
        if (x < 0 || x >= rows || y < 0 || y >= columns || grid[x][y] == '0') {
            return 0;
        }

        // recursively visit neignbors
        grid[x][y] = '0'; // marked as visited
        for (int[] direction : directions) {
            int neiborX = x + direction[0];
            int neiborY = y + direction[1];
            dfs(grid, neiborX, neiborY, rows, columns);
        }
        return 1;
    }

    /**
     * Solution(BFS)
     * https://leetcode.com/problems/number-of-islands/solution/
     * Time Complexity: O(m * n), where m, n are rows and columns
     * Space Complexity: O(min(m * n)), because in worst case where the grid is filled with lands, the size of queue can grow up to min(M,N).
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        // check corner cases
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        final int rows = grid.length;
        final int columns = grid[0].length;
        int islands = 0;
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[i].length; ++j) {
                if (grid[i][j] == '1') {
                    ++islands;
                    grid[i][j] = '0'; // mark as visited
                    Queue<Integer> neighbors = new LinkedList<>();
                    neighbors.add(hash(i, columns, j));
                    while (!neighbors.isEmpty()) {
                        int id = neighbors.remove();
                        int row = id / columns;
                        int column = id % columns;
                        int upRow = row - 1;
                        int downRow = row + 1;
                        int leftColumn = column - 1;
                        int rightColumn = column + 1;

                        // look up
                        if (upRow >= 0 && grid[upRow][column] == '1') {
                            neighbors.add(hash(upRow, columns, column));
                            grid[upRow][column] = '0';
                        }

                        // look down
                        if (downRow < rows && grid[downRow][column] == '1') {
                            neighbors.add(hash(downRow, columns, column));
                            grid[downRow][column] = '0';
                        }

                        // look left
                        if (leftColumn >= 0 && grid[row][leftColumn] == '1') {
                            neighbors.add(hash(row, columns, leftColumn));
                            grid[row][leftColumn] = '0';
                        }

                        // look right
                        if (rightColumn < columns && grid[row][rightColumn] == '1') {
                            neighbors.add(hash(row, columns, rightColumn));
                            grid[row][rightColumn] = '0';
                        }
                    }
                }
            }
        }

        return islands;
    }

    // f(x) = a * multipler + b
    private int hash(int a, int multipler, int b) {
        return a * multipler + b;
    }

    public static void main(String[] args) {
        LC200 solution = new LC200();
        char[][][] grids = {
            {},
            {{'1', '1', '1', '1', '0'}, {'1', '1', '0', '1', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '0', '0', '0'}},
            {{'1', '1', '0', '0', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '1', '0', '0'}, {'0', '0', '0', '1', '1'}}
        };
        int[] answers = {0, 1, 3};

        System.out.println("200. Number of Islands [medium]");
        for (int i = 0; i < grids.length; i++) {
            char[][] grid = grids[i];
            int answer = answers[i];
            System.out.println("Example " + (i + 1) + ":");
            System.out.println("Input:");
            solution.printGrid(grid);
            int output = solution.numIslands(grid);
            System.out.println("Output: " + output);
            System.out.println("Answer: " + answer);
            System.out.println("Score: " + (answer == output));
            System.out.println();
        }
    }

    private void printGrid(char[][] grid) {
        for (char[] rows : grid) {
            for (char column : rows) {
                System.out.print(column);
            }
            System.out.println();
        }
    }
}
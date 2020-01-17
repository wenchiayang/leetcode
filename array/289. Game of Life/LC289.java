import java.util.Arrays;

/**
 * LC289
 */
public class LC289 {
    final static int[][] points = {
        {0, 1}, {0, -1}, {-1, 0}, {1, 0}, // up, down, left, right
        {-1, 1}, {-1, -1}, {1, 1}, {1, -1} // left-up, left-right, right-up, roght-down
    };

    final static int die2Live = -1;
    final static int live2Die = 2;
    final static int live = 1;
    final static int die = 0;

    /**
     * Solution
     * https://www.youtube.com/watch?v=9AsUixzUGa0
     * mark die -> live: -1
     * mark live -> die: 2
     * count(board, i, j); 
     * update(); -1 => 1, 2 => 0
     * Time Complexity: O(m*n), when m, n are rows and columns of board
     * Space Complexity: O(1)
     * @param board
     */
    public void gameOfLife(int[][] board) {
        // check edge cases
        if (board == null || board.length == 0 || board[0].length == 0) {
            return;
        }

        // transform board into new state
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                int lives = countLives(board, i, j);

                if (board[i][j] == die) {
                    if (lives == 3) { // check condition 4
                        board[i][j] = die2Live;
                    }
                }

                if (board[i][j] == live) {
                    if (lives < 2 || lives > 3) {
                        board[i][j] = live2Die;
                    }
                }
            }
        }

        // update board
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == die2Live) {
                    board[i][j] = live;
                }

                if (board[i][j] == live2Die) {
                    board[i][j] = die;
                }
            }
        }
    }

    private int countLives(int[][] board, int row, int column) {
        int lives = 0;

        for (int[] point : points) {
            int newRow = row + point[0];
            int newColumn = column + point[1];

            // check boundries
            if (newRow >= 0 && newRow < board.length && newColumn >= 0 && newColumn < board[0].length) {
                int current = board[newRow][newColumn];

                // we count the original board, so we ignore condition 4
                if (current == live || current == live2Die) {
                    lives++;
                }
            }
        }

        return lives;
    }

    /**
     * Brute Force
     * Time Complexity: O(m*n), when m, n are rows and columns of board
     * Space Complexity: O(m*n), when m, n are rows and columns of board
     * @param board
     */
    public void gameOfLife2(int[][] board) {
        // check edge cases
        if (board == null || board.length == 0 || board[0].length == 0) {
            return;
        }

        int[][] result = new int[board.length][board[0].length];

        // make a new result board
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                result[i][j] = board[i][j];
                result[i][j] = update(board, i, j);
            }
        }

        // update board
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = result[i][j];
            }
        }
    }

    private int getLiveCount(int[][] board, int row, int column) {
        int lives = 0;

        // neighbor points
        for (int[] point : points) {
            int newRow = row + point[0];
            int newColumn = column + point[1];

            // check boundries of newRow and newColumn
            if (newRow >= 0 && newRow < board.length && newColumn >= 0 && newColumn < board[0].length) {
                if (board[newRow][newColumn] == 1) {
                    lives++;
                }
            }
        }

        return lives;
    }

    private int update(int[][] board, int row, int column) {
        int lives = getLiveCount(board, row, column);
        int current = board[row][column];
        int result = current;

        if (current == live) { // check 1~3
            if (lives < 2 || lives > 3) {
                result = die;
            }
        }

        if (current == die) {
            if (lives == 3) {
                result = live;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        LC289 solution = new LC289();
        int[][] board = {
            {0, 1, 0},
            {0, 0, 1},
            {1, 1, 1},
            {0, 0, 0},
        };
        int[][] answer = {
            {0, 0, 0},
            {1, 0, 1},
            {0, 1, 1},
            {0, 1, 0},
        };

        System.out.println("289. Game of Life [medium]");
        System.out.println("Example:");
        System.out.println("Input : " + Arrays.deepToString(board));
        solution.gameOfLife(board);
        System.out.println("Output: " + Arrays.deepToString(board));
        System.out.println("Answer: " + Arrays.deepToString(answer));
        boolean score = true;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != answer[i][j]) {
                    score = false;
                    break;
                }
            }
        }
        System.out.println("Score: " + score);
        System.out.println();
    }
}
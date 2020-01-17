import java.util.Arrays;

/**
 * LC243
 */
public class LC243 {

    public int shortestDistance(String[] words, String word1, String word2) {
        // check edge cases
        if (words == null || words.length == 0) {
            return 0;
        }

        int index1 = -1, index2 = -1, shortest = Integer.MAX_VALUE;

        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1)) {
                index1 = i;

                if (index2 != -1) {
                    shortest = Math.min(shortest, Math.abs(index1 - index2));
                }
            }

            if (words[i].equals(word2)) {
                index2 = i;

                if (index1 != -1) {
                    shortest = Math.min(shortest, Math.abs(index1 - index2));
                }
            }
        }

        // return -1 if not found
        return (shortest == Integer.MAX_VALUE) ? -1 : shortest;
    }
    /**
     * My Solution(One-pass)
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * @param words
     * @param word1
     * @param word2
     * @return
     */
    public int shortestDistance2(String[] words, String word1, String word2) {
        // check edge cases
        if (words == null || words.length == 0) {
            return 0;
        }

        int position1 = -1, position2 = -1;
        int shortestDistance = -1;

        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1)) {
                position1 = i;
            }

            if (words[i].equals(word2)) {
                position2 = i;
            }

            if (position1 != -1 && position2 != -1) {
                int distance = Math.abs(position1 - position2);

                if (shortestDistance == -1) {
                    shortestDistance = distance;
                } else {
                    shortestDistance = Math.min(distance, shortestDistance);
                }
            } 
        }

        return shortestDistance;
    }

    public static void main(String[] args) {
        LC243 solution = new LC243();
        // String[] words = {"practice", "makes", "perfect", "coding", "makes"};
        // String[][] inputs = {
        //     {"coding", "practice"},
        //     {"makes", "coding"},
        // };
        // int[] answers = {3, 1};
        String[] words = {"a", "a", "b", "b"};
        String[][] inputs = {
            {"a", "b"}
        };
        int[] answers = {1};
        
        System.out.println("243. Shortest Word Distance [easy]");
        System.out.println("words: " + Arrays.toString(words));
        for (int i = 0; i < inputs.length; i++) {
            String word1 = inputs[i][0];
            String word2 = inputs[i][1];
            System.out.println("Input: word1 = " + word1 + ", word2 = " + word2);
            int output = solution.shortestDistance(words, word1, word2);
            int answer = answers[i];
            System.out.println("Output: " + output);
            System.out.println("Answer: " + answer);
            System.out.println("Score: " + (answer == output));
            System.out.println();
        }
    }
}
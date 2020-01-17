import java.util.Arrays;

/**
 * LC245
 */
public class LC245 {
    /**
     * My Solution(one-pass)
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * @param words
     * @param word1
     * @param word2
     * @return
     */
    public int shortestWordDistance(String[] words, String word1, String word2) {
        // check edge cases
        if (words == null || words.length == 0) {
            return -1;
        }

        int index1 = -1, index2 = -1, shortest = Integer.MAX_VALUE;
        for (int i = 0; i < words.length; i++) {
            String word = words[i];

            if (word1.equals(word2)) {
                if (word.equals(word1)) {
                    if (index1 != -1) {
                        shortest = Math.min(shortest, Math.abs(index1 - i));
                    }
                    index1 = i;
                }
            } else {
                if (word.equals(word1)) {
                    if (index2 != -1) {
                        shortest = Math.min(shortest, Math.abs(index2 - i));
                    }
                    index1 = i;
                }

                if (word.equals(word2)) {
                    if (index1 != -1) {
                        shortest = Math.min(shortest, Math.abs(index1 - i));
                    }
                    index2 = i;
                }
            }
        }

        return (shortest == Integer.MAX_VALUE) ? -1 : shortest;
    }

    /**
     * My Solution(one-pass)
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * @param words
     * @param word1
     * @param word2
     * @return
     */
    public int shortestWordDistance2(String[] words, String word1, String word2) {
        // check edge cases
        if (words == null || words.length == 0) {
            return -1;
        }
        
        int pos1 = -1, pos2 = -1, shortest = -1;
        boolean pos1_open = true, pos2_open = true;
        
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1)) {
                if (word1.equals(word2)) {
                    if (pos1_open) {
                        pos1 = i;
                
                        pos1_open = false;
                        pos2_open = true;
                    }
                } else {
                    pos1 = i;
                }                
            }
            
            if (words[i].equals(word2)) {
                if (word1.equals(word2)) {
                    if (pos2_open && pos1 != i) {
                        pos2 = i;
                
                        pos1_open = true;
                        pos2_open = false;
                    }
                } else {
                    pos2 = i;
                }
            }
            
            if (pos1 != -1 && pos2 != -1) {
                int dist = Math.abs(pos1 - pos2);
                
                if (shortest == -1) {
                    shortest = dist;
                } else {
                    shortest = Math.min(shortest, dist);
                }
            }
        }
        
        return shortest;
    }

    public static void main(String[] args) {
        LC245 solution = new LC245();
        // String[] words = {"practice", "makes", "perfect", "coding", "makes"};
        // String[][] inputs = {
        //     {"makes", "coding"},
        //     {"makes", "makes"}
        // };
        // int[] answers = {1, 3};
        String[] words = {"a", "c", "a", "b"};
        String[][] inputs = {
            {"a", "b"}
        };
        int[] answers = {1};
        
        System.out.println("245. Shortest Word Distance III [medium]");
        System.out.println("words: " + Arrays.toString(words));
        // for (int i = 0; i < inputs.length; i++) {
        for (int i = inputs.length - 1; i >= 0; i--) {
            String word1 = inputs[i][0];
            String word2 = inputs[i][1];
            System.out.println("Input: word1 = " + word1 + ", word2 = " + word2);
            int output = solution.shortestWordDistance(words, word1, word2);
            int answer = answers[i];
            System.out.println("Output: " + output);
            System.out.println("Answer: " + answer);
            System.out.println("Score: " + (answer == output));
            System.out.println();
        }
    }
}
import java.util.ArrayList;
import java.util.List;

public class LC383 {
    /**
     * Solution
     * https://www.polarxiong.com/archives/LeetCode-383-ransom-note.html
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] magazineCounter = new int[26];

        // count magazine char
        for (char c : magazine.toCharArray()) {
            int position = c - 'a';
            magazineCounter[position]++;
        }

        for (int i = 0; i < ransomNote.length(); i++) {
            int position = ransomNote.charAt(i) - 'a';

            // check if we can use char in magazine and put it into ransom note
            if (magazineCounter[position] == 0) {
                return false;
            }

            // update magazine counter
            magazineCounter[position]--;
        }

        return true;
    }

    public static void main(String[] args) {
        LC383 solution = new LC383();
        System.out.println("383. Ransom Note [easy]");
        String[][] inputs = {
            {"a", "b"},
            {"aa", "ab"},
            {"aa", "aab"},
            {"fffbfg", "effjfggbffjdgbjjhhdegh"}
        };
        boolean[] answers = {false, false, true, true};

        for (int i = 0; i < inputs.length; i++) {
            String[] input = inputs[i];
            String ransomNote = input[0];
            String magazine = input[1];
            boolean output = solution.canConstruct(ransomNote, magazine);
            boolean answer = answers[i];
            boolean score = output == answer;

            System.out.println("canConstruct('" + ransomNote + "',' " + magazine + "') -> " + output);
            System.out.println("Answer: " + answer);
            System.out.println("Score: " + score);
            System.out.println();
        }
    }
}
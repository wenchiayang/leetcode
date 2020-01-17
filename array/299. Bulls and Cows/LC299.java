import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * LC299
 */
public class LC299 {
    /**
     * Solution(One pass)
     * https://leetcode.com/problems/bulls-and-cows/discuss/74621/One-pass-Java-solution
     * Time Complexity: O(l), where l is the lenght of secret/guess
     * Space Complexity: O(l), where l is the lenght of secret/guess
     * @param secret
     * @param guess
     * @return
     */
    public String getHint(String secret, String guess) {
        int bulls = 0, cows = 0;
        int[] counter = new int[10]; // count of 0~9

        for (int i = 0; i < secret.length(); i++) {
            if (secret.charAt(i) == guess.charAt(i)) {
                bulls++;
            } else {
                // if prev part of guess has curr digit in secret
                // then we found a pair that has same digit with different position
                if (counter[secret.charAt(i) - '0'] < 0) {
                    cows++;
                }

                // if prev part of secret has curr digit in guess
                // then we found a pair that has same digit with different position  
                if (counter[guess.charAt(i) - '0'] > 0) { 
                    cows++;
                }

                counter[secret.charAt(i) - '0']++;
                counter[guess.charAt(i) - '0']--;
            }
        }

        return bulls + "A" + cows + "B";
    }

    /**
     * Brute Force(Better Version)
     * Time Complexity: O(l), where l is the lenght of secret/guess
     * Space Complexity: O(l), where l is the lenght of secret/guess
     * @param secret
     * @param guess
     * @return
     */
    public String getHint22(String secret, String guess) {
        int A = 0, B = 0;

        // check edge cases
        if (secret.isEmpty() || guess.isEmpty()) {
            return generate(A, B);
        } 

        // find A and count counters with secret
        Map<Character, Integer> secretWordCounter = new HashMap<>();
        for (int i = 0; i < secret.length(); i++) {
            if (secret.charAt(i) == guess.charAt(i)) {
                A++;
            } else {
                secretWordCounter.put(secret.charAt(i), secretWordCounter.getOrDefault(secret.charAt(i), 0) + 1);
            }
        }

        // calulate B
        for (int i = 0; i < secret.length(); i++) {
            if (secret.charAt(i) != guess.charAt(i)) {
                // only increase when guess(i) appears in secretWordCounter and count > 0
                if (secretWordCounter.containsKey(guess.charAt(i)) && secretWordCounter.get(guess.charAt(i)) > 0) {
                    secretWordCounter.put(guess.charAt(i), secretWordCounter.get(guess.charAt(i)) - 1);
                    B++;
                }
            }
        }

        return generate(A, B);
    }

    /**
     * Brute Force
     * Time Complexity: O(l), where l is the lenght of secret/guess
     * Space Complexity: O(l), where l is the lenght of secret/guess
     * @param secret
     * @param guess
     * @return
     */
    public String getHint2(String secret, String guess) {
        int bulls = 0, cows = 0;
        
        // check edge cases
        if (secret.isEmpty() || guess.isEmpty()) {
            return generate(bulls, cows);
        }

        // calculate bulls
        List<Integer> bullIndice = new ArrayList<>();
        for (int i = 0; i < secret.length(); i++) {
            if (secret.charAt(i) == guess.charAt(i)) {
                bulls++;
                bullIndice.add(i);
            }
        }

        // build word counters of secret and guess
        Map<Character, Integer> secretWordCounter = new HashMap<>();
        Map<Character, Integer> guessWordCounter = new HashMap<>();
        // only count without bull index
        for (int i = 0; i < secret.length(); i++) {
            if (!bullIndice.contains(i)) {
                secretWordCounter.put(secret.charAt(i), secretWordCounter.getOrDefault(secret.charAt(i), 0) + 1);
                guessWordCounter.put(guess.charAt(i), guessWordCounter.getOrDefault(guess.charAt(i), 0) + 1);
            }
        }
        
        // calculate cows
        for (char c : guessWordCounter.keySet()) {
            if (secretWordCounter.containsKey(c)) {
                cows += Math.min(secretWordCounter.get(c), guessWordCounter.get(c));
            }
        }

        return generate(bulls, cows);
    }

    private String generate(int bulls, int cows) {
        return bulls + "A" + cows + "B";
    }

    public static void main(String[] args) {
        LC299 solution = new LC299();
        String[][] inputs = {
            {"2822013305", "5706322849"},
            {"1122", "2211"},
            {"1807", "7810"},
            {"1123", "0111"},
            {"", ""}
        };
        String[] answers = {
            "0A6B", "0A4B", "1A3B", "1A1B", "0A0B"
        };

        System.out.println("299. Bulls and Cows [easy]");
        for (int i = 0; i < inputs.length; i++) {
            System.out.println("Example: " + (i + 1));
            String[] input = inputs[i];
            String secret = input[0];
            String guess = input[1];
            System.out.println("Input: secret = " + secret + ", guess = " + guess);
            String output = solution.getHint(secret, guess);
            System.out.println("Output: " + output);
            String answer = answers[i]; 
            System.out.println("Answer: " + answer);
            System.out.println("Score: " + (answer.equals(output)));
            System.out.println();
        }
    }
}
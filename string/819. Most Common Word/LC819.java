import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LC819 {
    public String mostCommonWord(String paragraph, String[] banned) {
        // check corner case
        // paragraph isEmpty or banned is null or empty
        if (paragraph.isEmpty() && (banned == null || banned.length == 0)) {
            return "";
        }
        
        // 1. banned word set
        // 2. words -> paragraph.split
        // 3. counter map (k,v) -> (word, count) to count appearance
        // 4. find answer
        Set<String> bannedWords = new HashSet<>();
        
        for (String  w : banned) {
            bannedWords.add(w);
        }
        
        String[] words = paragraph.toLowerCase().split("\\W");
        Map<String, Integer> counter = new HashMap<>();
        for (String word : words) {
            if (!bannedWords.contains(word)) {
                counter.put(word, counter.getOrDefault(word, 0) + 1);
            }
        }
        
        String answer = "";
        int max = 0;
        
        for (String key : counter.keySet()) {
            int count = counter.get(key);
            
            if (count > max) {
                max = count;
                answer = key;
            }
        }
        
        return answer;
    }

    public static void main(String[] args) {
        System.out.println("819. Most Common Word [easy]");
        LC819 solution = new LC819();
        String paragraph = "Bob hit a ball, the hit BALL flew far after it was hit.";
        String[] banned = {"hit"};
        String output = solution.mostCommonWord(paragraph, banned);
        String answer = "ball";
        System.out.println("Input: " + paragraph);
        System.out.println("Banned: " + Arrays.toString(banned));
        System.out.println("Output: " + output);
        System.out.println("Answer: " + answer);
        System.out.println("Score: " + answer.equals(output));
    }
}
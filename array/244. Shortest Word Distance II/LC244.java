import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LC244
 */
/**
 * Solution
 * https://www.youtube.com/watch?v=BBJRj3Ku2P8
 * https://leetcode.com/problems/shortest-word-distance-ii/solution/
 */
class WordDistance {
    Map<String, List<Integer>> map = new HashMap<>();

    public WordDistance(String[] words) {
        for (int i = 0; i < words.length; i++) {
            if (map.containsKey(words[i])) {
                map.get(words[i]).add(i);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                map.put(words[i], list);
            }
        }
    }
    
    public int shortest(String word1, String word2) {
        List<Integer> l1 = map.get(word1);
        List<Integer> l2 = map.get(word2);

        // indices to loop l1 and l2
        int curr1 = 0, curr2 = 0;
        int shortest = Integer.MAX_VALUE;

        while (curr1 < l1.size() && curr2 < l2.size()) {
            shortest = Math.min(shortest, Math.abs(l1.get(curr1) - l2.get(curr2)));
            
            if (l1.get(curr1) < l2.get(curr2)) {
                curr1++;
            } else {
                curr2++;
            }
        }

        return (shortest == Integer.MAX_VALUE) ? -1 : shortest;
    }
}
/**
 * Your WordDistance object will be instantiated and called as such:
 * WordDistance obj = new WordDistance(words);
 * int param_1 = obj.shortest(word1,word2);
 */
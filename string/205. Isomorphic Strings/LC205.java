import java.util.Map;
import java.util.Set;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class LC205 {
    /**
     * Solution
     * https://www.youtube.com/watch?v=Niv_Io4k06c
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * @param s
     * @param t
     * @return
     */
    public boolean isIsomorphic(String s, String t) {
        // check corner cases
        if (s == null || t == null || s.length() != t.length()) {
            return false;
        }

        // the mapping result should be the same in the base of both s and t
        // e.g. s = ab, t = aa -> should return false
        return checkIsomorphic(s, t) && checkIsomorphic(t, s);
    }

    private boolean checkIsomorphic(String s1, String s2) {
        Map<Character, Character> map = new HashMap<>();

        for (int i = 0; i < s1.length(); i++) {
            if (!map.containsKey(s1.charAt(i))) {
                map.put(s1.charAt(i), s2.charAt(i));
            } 
            
            if (map.get(s1.charAt(i)) != s2.charAt(i)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Naive Method
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * @param s
     * @param t
     * @return
     */
    public boolean isIsomorphic2(String s, String t) {
        // check corner cases
        if (s == null || t == null || s.length() != t.length()) {
            return false;
        }

        // (k, v) = (origin, new)
        // map origin and new char
        Map<Character, Character> map = new HashMap<>();
        // store already used char
        Set<Character> used = new HashSet<>();

        int len = s.length();

        // replaced_array should equal t finally
        // because we add t's char in here
        char[] replaced_array = new char[len];

        for (int i = 0; i < len; i++) {
            // if # of c in t not in the map
            // add # of c in t to the map and used
            if (!map.containsKey(s.charAt(i))) {
                // if # of c in t in the used -> fail
                if (used.contains(t.charAt(i))) {
                    return false;
                }
                map.put(s.charAt(i), t.charAt(i));
                used.add(t.charAt(i));
            }

            // if # of c in s != # of c in t -> fail
            if (map.get(s.charAt(i)) != t.charAt(i)) {
                return false;
            }

            replaced_array[i] = map.get(s.charAt(i));
        }

        return new String(replaced_array).equals(t);
    }
    
    public static void main(String[] args) {
        System.out.println("205. Isomorphic Strings [easy]");

        String[] ss = {"ab", "egg", "foo", "paper", ""};
        String[] ts = {"aa", "add", "bar", "title", ""};
        boolean[] answers = {false, true, false, true, true};
        LC205 solution = new LC205();

        for (int i = 0; i < ss.length; i++) {
            System.out.println("Example " + (i + 1) + ":");
            String s = ss[i];
            String t = ts[i];
            boolean output = solution.isIsomorphic(s, t);
            boolean answer = answers[i];
            boolean score = output == answer;

            System.out.println("Input: s = " + s + ", t = " + t);
            System.out.println("Output: " + output);
            System.out.println("Answer: " + answer);
            System.out.println("Score: " + score);
            System.out.println();
        }
    }
}
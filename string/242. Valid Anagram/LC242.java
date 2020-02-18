
public class LC242 {
    /**
     * Time Complexity: O(n), n is len(s)
     * Space Complexity: (1)
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram(String s, String t) {
        // check corner cases
        if (s == null || t == null || s.length() != t.length()) {
            return false;
        }

        int[] s_counter = makeCounter(s);
        int[] t_counter = makeCounter(t);

        for (int i = 0; i < 26; i++) {
            if (s_counter[i] != t_counter[i]) {
                return false;
            }
        }

        return true;
    }

    private int[] makeCounter(String s) {
        int[] counter = new int[26];

        for (char c : s.toCharArray()) {
            counter[c - 'a']++;
        }

        return counter;
    }

    public static void main(String[] args) {
        System.out.println("242. Valid Anagram [easy]");

        String[] ss = {"anagram", "rat"};
        String[] ts = {"nagaram", "car"};
        boolean[] answers = {true, false};
        LC242 solution = new LC242();

        for (int i = 0; i < ss.length; i++) {
            System.out.println("Example " + (i + 1) + ":");
            String s = ss[i];
            String t = ts[i];
            boolean output = solution.isAnagram(s, t);
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
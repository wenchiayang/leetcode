
public class LC557 {
    /**
     * Naive Method
     * Time Complexity: O(n ^ 2)
     * Space Complexity: O(n)
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        // check corner cases
        if (s == null || s.length() == 0) return "";

        String[] words = s.split(" ");
        String result = "";

        for (int i = 0; i < words.length; i++) {
            if (i == words.length - 1) {
                result += reverseWord(words[i]);
            } else {
                result += reverseWord(words[i]) + " ";
            }
        }
        
        return result;
    }

    private String reverseWord(String s) {
        // check corner cases
        if (s == null || s.length() == 0) return "";
        int left = 0, right = s.length() - 1;
        char[] array = s.toCharArray();

        while (left < right) {
            char temp = array[left];
            array[left] = array[right];
            array[right] = temp;

            left++;
            right--;
        }

        return new String(array);
    } 

    public static void main(String[] args) {
        System.out.println("557. Reverse Words in a String III [easy]");

        String[] inputs = {"Let's take LeetCode contest"};
        String[] answers = {"s'teL ekat edoCteeL tsetnoc"};
        LC557 solution = new LC557();

        for (int i = 0; i < inputs.length; i++) {
            System.out.println("Example " + (i + 1) + ":");
            String s = inputs[i];
            String output = solution.reverseWords(s);
            String answer = answers[i];
            boolean score = output.equals(answer);

            System.out.println("Input: " + s);
            System.out.println("Output: " + output);
            System.out.println("Answer: " + answer);
            System.out.println("Score: " + score);
            System.out.println();
        }
    }
}
public class LC13 {
    /**
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * @param s
     * @return
     */
    public int romanToInt(String s) {
        s = s.toUpperCase();

        // check edge case
        if (s == null || s.isEmpty()) {
            return 0;
        }

        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            if (i > 0) {
                sum = adjust(s.charAt(i - 1), s.charAt(i), sum);
            }

            sum += getValue(s.charAt(i));
        }

        return sum;
    }

    // deal with special cases
    private int adjust(char a, char b, int sum) {
        if ((a == 'I' && b == 'V') || (a == 'I' && b == 'X')) {
            sum -= 2;
        } else if ((a == 'X' && b == 'L') || (a == 'X' && b == 'C')) {
            sum -= 20;
        } else if ((a == 'C' && b == 'D') || (a == 'C' && b == 'M')) {
            sum -= 200;
        }

        return sum;
    }

    private int getValue(char c) {
        if (c == 'I') return 1;
        if (c == 'V') return 5;
        if (c == 'X') return 10;
        if (c == 'L') return 50;
        if (c == 'C') return 100;
        if (c == 'D') return 500;
        if (c == 'M') return 1000;
        return 0;
    }
    
    public static void main(String[] args) {
        LC13 solution = new LC13();
        System.out.println("13. Roman to Integer [easy]");
        String[] inputs = {
            "III", "IV", "IX", "LVIII", "MCMXCIV"
        };
        int[] answers = {3, 4, 9, 58, 1994};

        for (int i = 0; i < inputs.length; i++) {
            System.out.println("Example " + (i + 1));
            String input = inputs[i];
            int output = solution.romanToInt(input);
            int answer = answers[i];
            System.out.println("Input: " + input);
            System.out.println("Output: " + output);
            System.out.println("Answer: " + answer);
            System.out.println("Score: " + (answer == output));
            System.out.println();
        }

    }
}
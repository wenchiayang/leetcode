/**
 * LC415
 */
public class LC415 {
    /**
     * Time Complexity: O(n), where n is Max(num1, num2) + 1
     * Space Complexity: O(n), where n is Max(num1, num2) + 1
     * @param num1
     * @param num2
     * @return
     */
    public String addStrings(String num1, String num2) {
        // check corner cases
        if (num1 == null && num2 == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        int index1 = num1.length() - 1;
        int index2 = num2.length() - 1;
        int carry = 0;

        while (index1 >= 0 || index2 >= 0) {
            int digit = 0;
            
            digit += carry;

            // if num1 has value -> add to digit
            if (index1 >= 0) {
                // digit += Integer.valueOf(String.valueOf(num1.charAt(index1)));
                digit += num1.charAt(index1) - '0';
                index1--;
            }

            // if num2 has value -> add to digit
            if (index2 >= 0) {
                // digit += Integer.valueOf(String.valueOf(num2.charAt(index2)));
                digit += num2.charAt(index2) - '0';
                index2--;
            }

            // calculate carry
            if (digit > 9) {
                digit %= 10;
                carry = 1;
            } else {
                carry = 0;
            }

            sb.append(digit);
        }

        // overflow, need to add to digit
        if (carry == 1) {
            sb.append(carry);
        }

        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println("415. Add Strings [easy]");
        LC415 solution = new LC415();

        String[] num1s = {
            "111", "9", "99"
        };
        String[] num2s = {
            "222", "11", "2"
        };
        String[] answers = {
            "333", "20", "101"
        };

        for (int i = 0; i < answers.length; i++) {
            System.out.println("Example " + (i + 1) + ":");
            String num1 = num1s[i];
            String num2 = num2s[i];
            String output = solution.addStrings(num1, num2);
            String answer = answers[i];
            boolean score = output.equals(answer);

            System.out.println("Input: num1 = " + num1 + ", num2 = " + num2);
            System.out.println("Output: " + output);
            System.out.println("Answer: " + answer);
            System.out.println("Score: " + score);
            System.out.println();
        }
    }
}
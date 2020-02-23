
public class LC67 {
    /**
     * Solution
     * https://www.youtube.com/watch?v=OEW50g03mT0
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * @param a
     * @param b
     * @return
     */
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int indexA = a.length() - 1;
        int indexB = b.length() - 1;
        int carry = 0;

        while (indexA >= 0 || indexB >= 0) {
            int sum = carry;

            if (indexA >= 0) {
                sum += a.charAt(indexA) - '0';
            }

            if (indexB >= 0) {
                sum += b.charAt(indexB) - '0';
            }

            sb.append(sum % 2);
            carry = sum / 2;

            indexA--;
            indexB--;
        }

        // handle carry
        if (carry != 0) {
            sb.append(carry);
        }

        return reverse(sb.toString());
    }

    private String reverse(String s) {
        char[] array = s.toCharArray();
        int left = 0, right = array.length - 1;

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
        System.out.println("67. Add Binary [easy]");

        String[] as = {"11", "1010"};
        String[] bs = {"1", "1011"};
        String[] answers = {"100", "10101"};
        LC67 solution = new LC67();

        for (int i = 0; i < as.length; i++) {
            System.out.println("Example " + (i + 1) + ":");
            String a = as[i];
            String b = bs[i];
            String output = solution.addBinary(a, b);
            String answer = answers[i];
            boolean score = output.equals(answer);

            System.out.println("Input: a = " + a + ", b = " + b);
            System.out.println("Output: " + output);
            System.out.println("Answer: " + answer);
            System.out.println("Score: " + score);
            System.out.println();
        }
    }
}
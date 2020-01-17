import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

/**
 * LC937
 */
public class LC937 {

    /**
     * Solution
     * Thinking: rewrite comparator
     * https://www.cnblogs.com/liuliu5151/p/11026126.html
     * Time Complexity: O(nlogn)
     * Space Complexity: O()
     * @param logs
     * @return
     */
    public String[] reorderLogFiles(String[] logs) {
        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                // split example
                // https://www.geeksforgeeks.org/split-string-java-examples/
                String[] split1 = s1.split(" ", 2);
                String[] split2 = s2.split(" ", 2);
                boolean isDigit1 = Character.isDigit(split1[1].charAt(0));
                boolean isDigit2 = Character.isDigit(split2[1].charAt(0));

                if (!isDigit1 && !isDigit2) {
                    int compare = split1[1].compareTo(split2[1]);
                    if (compare != 0) {
                        return compare;
                    }
                    return split1[0].compareTo(split2[0]);
                }

                return isDigit1 ? (isDigit2 ? 0 : 1) : -1;
            }
        };
        Arrays.sort(logs, comparator);
        return logs;
    }

    public static void main(String[] args) {
        LC937 solution = new LC937();
        String[][] inputs = {
            {"dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"},
            {}
        };
        String[][] answers = {
            {"let1 art can","let3 art zero","let2 own kit dig","dig1 8 1 5 1","dig2 3 6"},
            {}
        };

        System.out.println("937. Reorder Data in Log Files [easy]");
        for (int i = 0; i < inputs.length; i++) {
            System.out.println("Example: " + (i + 1));
            System.out.println("Input: logs = " + Arrays.toString(inputs[i]));
            String[] output = solution.reorderLogFiles(inputs[i]);
            System.out.println("Output: " + Arrays.toString(output));
            System.out.println("Answer: " + Arrays.toString(answers[i]));
            System.out.println();
        }
    }
}
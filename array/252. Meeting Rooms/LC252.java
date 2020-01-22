import java.util.Arrays;
import java.util.Comparator;

public class LC252 {
    /**
     * Solution
     * Idea: Custom Comparator
     * https://leetcode.com/problems/meeting-rooms/solution/
     * Time Complexity: O(nlogn), because of Arrays.sort()
     * Space Complexity: O(1)
     * @param intervals
     * @return
     */
    public boolean canAttendMeetings(int[][] intervals) {
        // check corner cases
        if (intervals == null || intervals.length == 0 || intervals[0].length == 0) {
            return true;
        }

        // define comparator
        Comparator<int[]> comparator = new Comparator<int[]>() {
            @Override
            public int compare(int[] t1, int[] t2) {
                // return t1[1] - t2[1]; // sort end time
                return t1[0] - t2[0]; // sort start time
            }
        };

        Arrays.sort(intervals, comparator);

        for (int i = 0; i < intervals.length - 1; i++) {
            // end time > next start time
            if (intervals[i][1] > intervals[i + 1][0]) {
                return false;
            }
        }

        return true;
    }

    /**
     * Solution
     * https://www.youtube.com/watch?v=i2bBG7CaVxs
     * Time Complexity: O(nlogn), because of Arrays.sort()
     * Space Complexity: O(n)
     * @param intervals
     * @return
     */
    public boolean canAttendMeetings2(int[][] intervals) {
        // check corner cases
        if (intervals == null || intervals.length == 0 || intervals[0].length == 0) {
            return true;
        }
        
        int[] starts = new int[intervals.length];
        int[] ends = new int[intervals.length];
        
        for (int i = 0; i < intervals.length; i++) {
            starts[i] = intervals[i][0];
            ends[i] = intervals[i][1];
        }
        
        Arrays.sort(starts);
        Arrays.sort(ends);
        
        // We ignore the last index of ends time
        for (int i = 0; i < intervals.length - 1; i++) {
            int end = ends[i];
            int nextStart = starts[i + 1];
            
            // find invalid case
            if (nextStart < end) {
                return false;
            }
        }
        
        return true;
    }

    /**
     * Solution
     * Brute Force
     * https://leetcode.com/problems/meeting-rooms/solution/
     * Time Complexity: O(n^2)
     * Space Complexity: O(1)
     * @param intervals
     * @return
     */
    public boolean canAttendMeetings3(int[][] intervals) {
        // check corner cases
        if (intervals == null || intervals.length == 0 || intervals[0].length == 0) {
            return true;
        }

        for (int i = 0; i < intervals.length; i++) {
            for (int j = i + 1; j < intervals.length; j++) {
                if (overlap(intervals[i], intervals[j])) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean overlap(int[] i1, int[] i2) {
        return (Math.min(i1[1], i2[1]) > Math.max(i1[0], i2[0]));
        // return ((i1[0] >= i2[0] && i1[0] < i2[1]) || (i2[0] >= i1[0] && i2[0] < i1[1]));
        // return !(i1[1] <= i2[0] || i1[0] >= i2[1]);
    }

    public static void main(String[] args) {
        LC252 solution = new LC252();
        int[][][] inputs = {
            {{0, 30}, {5, 10}, {15, 20}},
            {{7, 10}, {2, 4}},
            {{}}
        };
        boolean[] answers = {false, true, true};
        
        System.out.println("252. Meeting Rooms [easy]");
        
        for (int i = 0; i < inputs.length; i++) {
            System.out.println("Example " + (i + 1) + ":");
            System.out.println("Input: " + Arrays.deepToString(inputs[i]));
            boolean output = solution.canAttendMeetings(inputs[i]);
            boolean answer = answers[i];
            System.out.println("Output: " + output);
            System.out.println("Answer: " + answer);
            System.out.println("Score: " + (answer == output));
            System.out.println();
        }
    }
}
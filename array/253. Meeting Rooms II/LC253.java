import java.util.Arrays;
import java.util.PriorityQueue;

public class LC253 {
    /**
     * Solution
     * Idea: minHeap in endtime
     * https://www.youtube.com/watch?v=4MEkBvqE_2Q
     * Time Complexity: O(nlogn), for both worst cases of arrays.sort and min-heap heapify
     * Space Complexity: O(n), when construct min heap
     * @param intervals
     * @return
     */
    public int minMeetingRooms(int[][] intervals) {
        // check corner cases
        if (intervals == null || intervals.length == 0 || intervals[0].length == 0) {
            return 0;
        }

        // sort intervals in ascending order by start times
        Arrays.sort(intervals, (a, b) -> (a[0] - b[0]));

        // create minHeap to store end time
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(
            (a, b) -> (a - b)
        );
        // add end time of first schedule to minHeap (need 1 room)
        minHeap.add(intervals[0][1]);
        
        // loop through the rest
        for (int i = 1; i < intervals.length; i++) {
            int currentStartTime = intervals[i][0];
            int earliestEndTime = minHeap.peek(); // look root node
            
            // '>=' means includes the edge case
            //  e.g. currentStartTime = 2, earliestEndTime = 2
            if (currentStartTime >= earliestEndTime) {
                minHeap.poll(); // remove root node
            }

            // add current end time to heap
            minHeap.add(intervals[i][1]);
        }

        return minHeap.size();
    }

    public static void main(String[] args) {
        LC253 solution = new LC253();
        int[][][] inputs = {
            {{0, 30}, {5, 10}, {15, 20}},
            {{7, 10}, {2, 4}},
            {{}}
        };
        int[] answers = {2, 1, 0};
        
        System.out.println("253. Meeting Rooms II [medium]");
        
        for (int i = 0; i < inputs.length; i++) {
            System.out.println("Example " + (i + 1) + ":");
            System.out.println("Input: " + Arrays.deepToString(inputs[i]));
            int output = solution.minMeetingRooms(inputs[i]);
            int answer = answers[i];
            System.out.println("Output: " + output);
            System.out.println("Answer: " + answer);
            System.out.println("Score: " + (answer == output));
            System.out.println();
        }
    }
}
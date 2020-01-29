import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class LC347 {
    /**
     * Solution
     * PriorityQueue
     * https://www.youtube.com/watch?v=32CULRMlKE0
     * Time Complexity: O(n*logk)
     * Space Complexity: O(n)
     * @param nums
     * @param k
     * @return
     */
    public List<Integer> topKFrequent(int[] nums, int k) {
        // step 1: define a counter map to count num, (k, v) = (num, count)
        // step 2: define a priority queue, descending
        // step 3: add tuple(num,count) into pq
        // step 4: build top k

        Map<Integer, Integer> counter = new HashMap<>();
        for (int num : nums) {
            int count = counter.getOrDefault(num, 0);
            counter.put(num, count + 1);
        }

        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>(
            (p1, p2) -> p1.getValue() - p2.getValue()
        );

        for (Map.Entry<Integer, Integer> entry : counter.entrySet()) {
            pq.add(entry);

            if (pq.size() > k) {
                pq.poll();
            }
        }

        List<Integer> freqs = new ArrayList<>();

        for (Map.Entry<Integer, Integer> entry : pq) {
            freqs.add(entry.getKey());
        }

        return freqs;
    }

    /**
     * Solution
     * Bucket Sort
     * https://www.youtube.com/watch?v=32CULRMlKE0
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * @param nums
     * @param k
     * @return
     */
    public List<Integer> topKFrequent_bucketSort(int[] nums, int k) {
        // step 1: define a number counter (k,v) = (num, count)
        // step 2: define a buckets, each bucket(count) store nums of same count
        // step 3: pick top k freqs
        
        // step1
        Map<Integer, Integer> counter = new HashMap<>();
        for (int num : nums) {
            int count = counter.getOrDefault(num, 0);
            counter.put(num, count + 1);
        }
        
        // step2: buckets size -> 0...len(num) + 1
        List<List<Integer>> buckets = new ArrayList<>(nums.length + 1);
        for (int i = 0; i <= nums.length; i++) {
            buckets.add(new ArrayList<>());
        }
        
        // key: num, value: count
        for (int num : counter.keySet()) {
            int count = counter.get(num);
            // put nums at index(count)
            buckets.get(count).add(num);
        }
        
        // step3: pick top k
        List<Integer> freqs = new ArrayList<>();
        for (int i = buckets.size() - 1; i >= 0 && k > 0; i--) {
            List<Integer> bucket = buckets.get(i);
            for (int j = 0; j < bucket.size() && k > 0; j++) {
                freqs.add(bucket.get(j));
                k--;
            }
        }
        
        return freqs;
    }

    public static void main(String[] args) {
        LC347 solution = new LC347();
        System.out.println("347. Top K Frequent Elements [medium]");
        int[][] inputs = {
            {1, 1, 1, 2, 2, 3},
            {1}
        };
        int[] ks = {2, 1};
        int[][] answers = {
            {1, 2},
            {1}
        };

        for (int i = 0; i < inputs.length; i++) {
            System.out.println("Example " + (i + 1));
            int[] input = inputs[i];
            int[] answer = answers[i];
            List<Integer> output = solution.topKFrequent(input, ks[i]);
            System.out.println("Input: numns = " + Arrays.toString(input) + ", k = " + ks[i]);
            System.out.println("Output: " + Arrays.toString(output.toArray()));
            System.out.println("Answer: " + Arrays.toString(answer));
            System.out.println();
        }
    }
}
import java.util.PriorityQueue;

/**
 * Solution
 * https://www.youtube.com/watch?v=hqqTBrQEmNc
 * PriorityQueue
 * Time Complexity: O(n * logk)
 * Space Complexity: O(k)
 */
public class KthLargest {
    private PriorityQueue<Integer> minHeap;
    private int k;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        // default: min heap
        minHeap = new PriorityQueue<>(k);
        for (int num : nums) add(num);
    }
    
    public int add(int val) {
        minHeap.offer(val);
        // System.out.println("(before) minHeap: " + Arrays.toString(minHeap.toArray()));
        if (minHeap.size() > k) minHeap.poll();
        // System.out.println("(after)  minHeap: " + Arrays.toString(minHeap.toArray()));
        // System.out.println();
        return minHeap.peek();
    }

    public static void main(String[] args) {
        int k = 3;
        int[] arr = {4, 5, 8, 2};
        KthLargest kthLargest = new KthLargest(k, arr);
        System.out.println("703. Kth Largest Element in a Stream [easy]");
        System.out.println("kthLargest.add(3): " + kthLargest.add(3));   // returns 4
        System.out.println("kthLargest.add(5): " + kthLargest.add(5));   // returns 5
        System.out.println("kthLargest.add(10): " + kthLargest.add(10)); // returns 5
        System.out.println("kthLargest.add(9): " + kthLargest.add(9));   // returns 8
        System.out.println("kthLargest.add(4): " + kthLargest.add(4));   // returns 8
    }
}
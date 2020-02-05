import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Solution
 * https://www.youtube.com/watch?v=hqqTBrQEmNc
 * PriorityQueue
 * Time Complexity: O(nlogn)
 * Space Complexity: O(k)
 */
public class LC703 {
    private List<Integer> list;
    private int k;

    public LC703(int k, int[] nums) {
        this.k = k;
        list = new ArrayList<>();
        for (int num : nums) list.add(num);
        Collections.sort(list);
    }
    
    public int add(int val) {
        list.add(val);
        Collections.sort(list);
        return list.get(list.size() - k);
    }

    public static void main(String[] args) {
        int k = 3;
        int[] arr = {4, 5, 8, 2};
        LC703 kthLargest = new LC703(k, arr);
        System.out.println("703. Kth Largest Element in a Stream [easy]");
        System.out.println("kthLargest.add(3): " + kthLargest.add(3));   // returns 4
        System.out.println("kthLargest.add(5): " + kthLargest.add(5));   // returns 5
        System.out.println("kthLargest.add(10): " + kthLargest.add(10)); // returns 5
        System.out.println("kthLargest.add(9): " + kthLargest.add(9));   // returns 8
        System.out.println("kthLargest.add(4): " + kthLargest.add(4));   // returns 8
    }
}
import java.util.Arrays;
import java.util.PriorityQueue;

public class LC973 {
    /**
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * @param points
     * @param K
     * @return
     */
    public int[][] kClosest(int[][] points, int K) {
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
            (p1, p2) -> getDistance(p2) - getDistance(p1)
        );
        
        for (int[] point : points) {
            maxHeap.add(point);
            
            if (maxHeap.size() > K) {
                maxHeap.poll();
            }
        }
        
        int[][] result = new int[K][2];
        int index = 0;
        
        while (!maxHeap.isEmpty()) {
            result[index++] = maxHeap.poll();
        }
        
        return result;
        
    }
    
    private int getDistance(int[] p) {
        return p[0] * p[0] + p[1] * p[1];
    }
    
    public static void main(String[] args) {
        LC973 solution = new LC973();
        System.out.println("973. K Closest Points to Origin [medium]");
        int[][] points = {{1, 3}, {-2, 2}};
        int K = 1;
        int[][] output = solution.kClosest(points, K);
        System.out.println("Input: points = " + Arrays.deepToString(points) + ", K = " + K);
        System.out.println("Output: points = " + Arrays.deepToString(output));
    }
}
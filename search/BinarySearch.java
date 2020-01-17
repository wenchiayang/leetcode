import java.util.Arrays;

/**
 * BinarySearch
 */
public class BinarySearch {
    public int bs_iterative(int[] arr, int target) {
        int left = 0, right = arr.length - 1;

        while (left <= right) {
            int middle = left + (right - left) / 2;

            if (arr[middle] == target) {
                // find target, return mid directly
                return middle;
            } else if (arr[middle] > target) { 
                // middle > taget, move right to mid - 1
                right = middle - 1;
            } else { 
                // middle < taget, move left to mid + 1
                left = middle + 1;
            }
        }

        return -1;
    }

    public int bs_recursive(int[] arr, int target) {
        return bs_recursive(arr, target, 0, arr.length - 1);
    }

    private int bs_recursive(int[] arr, int target, int left, int right) {
        if (left <= right) {
            int middle = left + (right - left) / 2;
            
            if (arr[middle] == target) {
                // find target, return mid directly
                return middle;
            } else if (arr[middle] > target) { 
                // middle > taget, move right to mid - 1
                return bs_recursive(arr, target, left, middle - 1);
            } else { 
                // middle < taget, move left to mid + 1
                return bs_recursive(arr, target, middle + 1, right);
            }
        } else {
            // can not find target
            return -1;
        }
    }

    public static void main(String[] args) {
        BinarySearch bs = new BinarySearch();
        int[][] arrs = {
            {10, 10},
            {2, 3, 4, 10, 40}
        };
        int[] targets = {11, 40};
        int[] answers = {-1, 4};
        
        System.out.println("Binary Search:");
        for (int i = 0; i < arrs.length; i++) {
            int[] arr = arrs[i];
            int target = targets[i];
            int answer = answers[i];
            int recursiveResult = bs.bs_recursive(arr, target);
            int iterativeResult = bs.bs_iterative(arr, target);
            System.out.println("arrs: " + Arrays.toString(arr) + ", target: " + target);
            System.out.println("recursiveResult: " + recursiveResult);
            System.out.println("iterativeResult: " + iterativeResult);
            System.out.println("answer: " + answer);
            System.out.println();
        }

    }
}
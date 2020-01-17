import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * BucketSort
 */
public class BucketSort {
    /**
     * Bucket Sort
     * https://www.youtube.com/watch?v=xsTrwVjFoPk
     * @param inputs
     */
    private void bucketSort(int[] inputs) {
        // Create buckets
        List<Integer>[] buckets = new ArrayList[10];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }

        // add elements to each buckets by hashing its index
        int divider = getDivider(findMax(inputs), buckets.length);
        for (int n : inputs) {
            int index = hash(n, divider);
            // int index = n * buckets.length / (max + 1);
            buckets[index].add(n);
        }

        // sort each bucket
        for (List<Integer> bucket : buckets) {
            Collections.sort(bucket);
        }

        // put sorted buckets into the original array
        int index = 0;
        for (List<Integer> bucket : buckets) {
            for (int n : bucket) {
                inputs[index++] = n;
            }
        }
    }

    // private 

    /**
     * Hash Function
     * https://www.youtube.com/watch?v=geVyIsFpxUs
     */
    private int hash(int n, int divider) {
        return (int) Math.floor((double) n / (double) divider);
    }

    // assume lenght of inputs is greater than 1
    private int findMax(int[] inputs) {
        int max = 0;

        for (int n : inputs) {
            if (n > max) {
                max = n;
            }
        }

        return max;
    }

    private int getDivider(int max, int bucketSize) {
        return (int) Math.ceil((double) (max + 1) / (double) bucketSize);
    }

    public static void main(String[] args) {
        BucketSort solution = new BucketSort();
        int[] input = {22, 45, 12, 8, 10, 6, 72, 33, 18, 50, 14};
        System.out.println("Bucket Sort:");
        System.out.println("Input:  " + Arrays.toString(input));
        solution.bucketSort(input);
        System.out.println("Output: " + Arrays.toString(input));
    }
}
import java.util.Arrays;
import java.util.Map;

public class LC204 {
    /**
     * Solution
     * https://www.youtube.com/watch?v=UMVa5fRKC8I
     * Time Complexity: O(n^2)
     * Space Complexity: O(1)
     */
    public int countPrimes(int n) {
        // default boolean array values are all fasle
        boolean[] primes = new boolean[n];
        // set all true
        for (int i = 0; i < primes.length; i++) {
            primes[i] = true;
        }

        for (int i = 2; i * i < n; i++) {
            if (primes[i]) {
                for (int j = i; j * i < n; j++) {
                    // because index of i * j is not a prime
                    primes[i * j] = false;
                }
            }
        }

        int count = 0;
        // we ignore 0 and 1
        for (int i = 2; i < primes.length; i++) {
            if (primes[i]) count++;
        }

        return count;
    }

    /**
     * Solution
     * Brute Force
     * https://blog.csdn.net/afei__/article/details/80638460
     * Time Complexity: O(n^2)
     * Space Complexity: O(1)
     */
    public int countPrimes_brute_force(int n) {
        if (n < 2) return 0;

        int count = 0;
        for (int i = 2; i < n; i++) {
            // if (isPrime(i)) count++;
            if (isPrime_optimized(i)) count++;
        }

        return count;
    }
    
    private boolean isPrime(int num) {
        int sqrt = (int) Math.sqrt(num);
        for (int i = 2; i <= sqrt; i++) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }

    private boolean isPrime_optimized(int num) {
        if (num <= 3) return num > 1;

        // Prime is always be 6x - 1 or 6x + 1
        // https://blog.csdn.net/afei__/article/details/80638460
        // 3. Continue Optimizing
        if (num % 6 != 1 && num % 6 != 5) return false;

        int sqrt = (int) Math.sqrt(num);
        for (int i = 5; i <= sqrt; i += 6) {
            if (num % i == 0 || num % (i + 2) == 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println("204. Count Primes [easy]");
        LC204 solution = new LC204();

        int[] nums = {2, 10, 499979, 1500000};
        int[] answers = {0, 4, 41537, 114155};

        for (int i = 0; i < answers.length; i++) {
            System.out.println("Example " + (i + 1) + ":");
            int n = nums[i];
            int output = solution.countPrimes(n);
            int answer = answers[i];
            boolean score = output == answer;

            System.out.println("Input: " + n);
            System.out.println("Output: " + output);
            System.out.println("Answer: " + answer);
            System.out.println("Score: " + score);
            System.out.println();
        }
    }
}
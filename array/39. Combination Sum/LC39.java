import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class LC39 {
    /**
     * Solution
     * Backtracking(DFS)
     * https://www.youtube.com/watch?v=irFtGMLbf-s
     * Time Complexity: O(2^n)
     * Space Complexity: O(n)
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> combinations = new ArrayList<>();

        // check corner cases
        if (candidates == null || candidates.length == 0) {
            return combinations;
        }

        // sort candidates
        Arrays.sort(candidates);

        // backtracking all combinations
        List<Integer> combination = new ArrayList<>();
        findCombinations(combinations, combination, candidates, target, 0);
        
        return combinations;
    }

    private void findCombinations(List<List<Integer>> combinations, List<Integer> combination, int[] candidates, int target, int start) {
        // base case
        if (target == 0) {
            combinations.add(new ArrayList<>(combination));
            return;
        }

        // dfs call
        for (int i = start; i < candidates.length; i++) {
            // invalid case, we break loop
            if (candidates[i] > target) {
                break;
            }

            combination.add(candidates[i]);
            findCombinations(combinations, combination, candidates, target - candidates[i], i);
            combination.remove(combination.size() - 1);
        }
    }

    public static void main(String[] args) {
        LC39 solution = new LC39();
        System.out.println("39. Combination Sum [medium]");
        int[][] inputs = {
            {2, 3, 6, 7},
            {2, 3, 5},
            {8, 7, 4, 3}
        };
        int[] targets = {7, 8, 11};
        int[][][] answers = {
            {{7}, {2, 2, 3}},
            {{2, 2, 2, 2}, {2, 3, 3}, {3, 5}},
            {{3, 4, 4}, {3, 8}, {4, 7}}
        };

        for (int i = 0; i < inputs.length; i++) {
            int[] candidates = inputs[i];
            int target = targets[i];
            System.out.println("Input: candidates = " + Arrays.toString(candidates) + ", target = " + target);
            List<List<Integer>> output = solution.combinationSum(candidates, target);
            System.out.println("Output: " + Arrays.deepToString(output.toArray()));
            System.out.println("Answer: " + Arrays.deepToString(answers[i]));
            System.out.println();
        }
    }
}
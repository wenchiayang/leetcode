import java.util.HashMap;
import java.util.Map;

public class LC437 {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    
        /* function to print level order traversal of tree*/
        void printLevelOrder() { 
            int h = height(this); 
            int i; 
            for (i = 1; i <= h; i++) 
                printGivenLevel(this, i); 
        } 
      
        /* Compute the "height" of a tree -- the number of 
        nodes along the longest path from the root node 
        down to the farthest leaf node.*/
        int height(TreeNode root) { 
            if (root == null) return 0;     
            /* compute  height of each subtree */
            int left = height(root.left); 
            int right = height(root.right); 
                
            /* use the larger one */
            return Math.max(left, right) + 1;
        } 
      
        /* Print nodes at the given level */
        void printGivenLevel (TreeNode root ,int level) { 
            if (root == null) {
                System.out.print("null ");
                return; 
            }
            if (level == 1) {
                System.out.print(root.val + " "); 
            } else if (level > 1) {
                printGivenLevel(root.left, level - 1); 
                printGivenLevel(root.right, level - 1); 
            } 
        } 
    }
    
    /**
     * Solution
     * Prefix sum(Better)
     * 1. The prefix stores the sum from the root to the current node in the recursion
     * 2. The map stores <prefix sum, frequency> pairs before getting to the current node. We can imagine a path from the root to the current node. The sum from any node in the middle of the path to the current node = the difference between the sum from the root to the current node and the prefix sum of the node in the middle.
     * 3. We are looking for some consecutive nodes that sum up to the given target value, which means the difference discussed in 2. should equal to the target value. In addition, we need to know how many differences are equal to the target value.
     * 4. Here comes the map. The map stores the frequency of all possible sum in the path to the current node. If the difference between the current sum and the target value exists in the map, there must exist a node in the middle of the path, such that from this node to the current node, the sum is equal to the target value.
     * 5. Note that there might be multiple nodes in the middle that satisfy what is discussed in 4. The frequency in the map is used to help with this.
     * 6. Therefore, in each recursion, the map stores all information we need to calculate the number of ranges that sum up to target. Note that each range starts from a middle node, ended by the current node.
     * 7. To get the total number of path count, we add up the number of valid paths ended by EACH node in the tree.
     * 8. Each recursion returns the total count of valid paths in the subtree rooted at the current node. And this sum can be divided into three parts:
     *    - the total number of valid paths in the subtree rooted at the current node's left child
     *    - the total number of valid paths in the subtree rooted at the current node's right child
     *    - the number of valid paths ended by the current node
     * ps. the prefix is counted from the top(root) to the bottom(leaves), and the result of total count is calculated from the bottom to the top
     * https://leetcode.com/problems/path-sum-iii/discuss/91878/17-ms-O(n)-java-Prefix-sum-method
     * Time Complexity: O(n)
     * Space Complexity: O(h)
     * @param root
     * @param sum
     * @return
     */
    public int pathSum_prefix(TreeNode root, int sum) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        return findPathSum(root, 0, sum, map);
    }

    private int findPathSum(TreeNode node, int currentSum, int target, Map<Integer, Integer> map) {
        // check base case
        if (node == null) {
            return 0;
        }

        // update the prefix sum by adding the current node val
        currentSum += node.val;

        // get the number of valid path, ended by the current node
        int numPathToCurr = map.getOrDefault(currentSum - target, 0);

        // update the map with the current sum, so the map is good to be passed to the next recursion
        map.put(currentSum, map.getOrDefault(currentSum, 0) + 1);

        // each recursion returns the total count of valid paths in the subtree rooted at the current node. And this sum can be divided into three parts:
        // the number of valid paths ended by the current node
        int count = numPathToCurr;

        // the total number of valid paths in the subtree rooted at the current node's left child
        count += findPathSum(node.left, currentSum, target, map);

        // the total number of valid paths in the subtree rooted at the current node's right child
        count += findPathSum(node.right, currentSum, target, map);

        // restore the map, as the recursion goes from the bottom to the top
        map.put(currentSum, map.get(currentSum) - 1);

        return count;
    }

    /**
     * Solution
     * Double Recursion(Slow)
     * https://leetcode.com/problems/path-sum-iii/discuss/91889/Simple-Java-DFS
     * https://www.youtube.com/watch?v=NTyOEYYyv-o
     * Time Complexity: O(n^2), in worst case (no branching)
     * Time Complexity: O(nlogn), in best case (balanced tree)
     * Space Complexity: O(n)
     * @param root
     * @param sum
     * @return
     */
    public int pathSum_recursive(TreeNode root, int sum) {
        int count = 0;

        // check corner case
        if (root == null) {
            return count;
        }

        // recursion the entire tree + left tree + right tree
        count += dfs(root, sum);
        count += pathSum_recursive(root.left, sum);
        count += pathSum_recursive(root.right, sum);

        return count;
    }

    private int dfs(TreeNode node, int remainingSum) {
        int count = 0;
        
        // base case
        if (node == null) {
            return count;
        }

        // process
        if (node.val == remainingSum) {
            count++;
        }

        // recursion left and right
        count += dfs(node.left, remainingSum - node.val);
        count += dfs(node.right, remainingSum - node.val);

        return count;
    }
    
    public static void main(String[] args) {
        System.out.println("437. Path Sum III [easy]");
        LC437 solution = new LC437();
        TreeNode[] trees = solution.generateTrees();
        int[] sums = {8};
        int[] answers = {3};
        
        for (int i = 0; i < answers.length; i++) {
            TreeNode root = trees[i];
            int sum = sums[0];
            int output_recursive = solution.pathSum_recursive(root, sum);
            int output_iterative = solution.pathSum_prefix(root, sum);
            int answer = answers[0];
            boolean score = (output_recursive == answer) && (output_iterative == answer);

            System.out.println("Example: ");
            System.out.print("Input: [ ");
            root.printLevelOrder();
            System.out.print("], sum = " + sum + "\n");
            System.out.println("Output(recursive): " + output_recursive);
            System.out.println("Output(iterative): " + output_iterative);
            System.out.println("Answer: " + answer);
            System.out.println("Score: " + score);
            System.out.println();
        }
    }

    private TreeNode[] generateTrees() {
        TreeNode t1 = new TreeNode(10);
        t1.left = new TreeNode(5);
        t1.right = new TreeNode(-3);
        t1.left.left = new TreeNode(3);
        t1.left.right = new TreeNode(2);
        t1.right.right = new TreeNode(11);
        t1.left.left.left = new TreeNode(3);
        t1.left.left.right = new TreeNode(-2);
        t1.left.right.right = new TreeNode(1);
        
        return new TreeNode[] {t1};
    }
}
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class LC257 {
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
     * Iterative
     * https://leetcode.com/problems/binary-tree-paths/solution/
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public List<String> binaryTreePaths_iterative(TreeNode root) {
        List<String> paths = new ArrayList<>();

        // check corner case
        if (root == null) return paths;

        Stack<TreeNode> nodeStack = new Stack<>();
        Stack<String> pathStack = new Stack<>();
        nodeStack.push(root);
        pathStack.push(Integer.toString(root.val));

        while (!nodeStack.isEmpty()) {
            TreeNode node = nodeStack.pop();
            String path = pathStack.pop();

            // reach the leaf node, we add path to the paths
            if (node.left == null && node.right == null) {
                paths.add(path);
            }

            // dfs to left
            if (node.left != null) {
                nodeStack.push(node.left);
                pathStack.push(path + "->" + Integer.toString(node.left.val));
            }

            // dfs to right
            if (node.right != null) {
                nodeStack.push(node.right);
                pathStack.push(path + "->" + Integer.toString(node.right.val));
            }
        }

        return paths;
    }

    /**
     * Solution
     * Recursive (DFS)
     * https://www.youtube.com/watch?v=H2D4HcVZq_g
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public List<String> binaryTreePaths_recursive(TreeNode root) {
        List<String> paths = new ArrayList<>();

        // check corner case
        if (root == null) return paths;

        String path = String.valueOf(root.val);
        // only 1 node -> return directly
        if (root.left == null && root.right == null) {
            paths.add(path);
            return paths;
        }

        // dfs to left
        if (root.left != null) {
            dfs(root.left, path, paths);
        }

        // dfs to right
        if (root.right != null) {
            dfs(root.right, path, paths);
        }
        
        return paths;
    }

    private void dfs(TreeNode node, String path, List<String> paths) {
        path += "->" + node.val;

        // reach the leaf node, we add path to the paths
        if (node.left == null && node.right == null) {
            paths.add(path);
            return;
        }

        // dfs to left
        if (node.left != null) {
            dfs(node.left, path, paths);
        }

        // dfs to right
        if (node.right != null) {
            dfs(node.right, path, paths);
        }
    }
    
    public static void main(String[] args) {
        System.out.println("257. Binary Tree Paths [easy]");
        LC257 solution = new LC257();
        TreeNode[] trees = solution.generateTrees();
        String[][] answers = {
            {"1->2->5", "1->3"}
        };
        
        TreeNode root = trees[0];
        String[] answer = answers[0];

        System.out.println("Example: ");
        System.out.print("Input(Recursive): [ ");
        root.printLevelOrder();
        System.out.print("]\n");
        List<String> output_recursive = solution.binaryTreePaths_recursive(root);
        System.out.println("Output(Recursive):" + Arrays.toString(output_recursive.toArray()));
        List<String> output_iterative = solution.binaryTreePaths_iterative(root);
        System.out.println("Output(Iterative):" + Arrays.toString(output_iterative.toArray()));
        System.out.println("Answer:           " + Arrays.toString(answer));
        System.out.println();
    }

    private TreeNode[] generateTrees() {
        TreeNode t1 = new TreeNode(1);
        t1.left = new TreeNode(2);
        t1.right = new TreeNode(3);
        t1.left.right = new TreeNode(5);
        return new TreeNode[] {t1};
    }
}
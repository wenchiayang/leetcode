import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LC102 {
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
            if (root == null) {
               return 0; 
            } else { 
                /* compute  height of each subtree */
                int lheight = height(root.left); 
                int rheight = height(root.right); 
                  
                /* use the larger one */
                if (lheight > rheight) {
                    return(lheight + 1); 
                } else {
                    return(rheight + 1);  
                }
            } 
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
                printGivenLevel(root.left, level-1); 
                printGivenLevel(root.right, level-1); 
            } 
        } 
    }

    /**
     * Solution(Recursion)
     * https://leetcode.com/problems/binary-tree-level-order-traversal/solution/
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder_recursive(TreeNode root) {
        List<List<Integer>> levels = new ArrayList<>();

        // check corner cases
        if (root == null) {
            return levels;
        }

        levels = levelOrderHelper(root, 0, levels);
        return levels;
    }

    private List<List<Integer>> levelOrderHelper(TreeNode node, int level, List<List<Integer>> levels) {
        // start the current level
        if (levels.size() == level) {
            levels.add(new ArrayList<>());
        }

        // add data to the current level
        levels.get(level).add(node.val);

        // process left and right node
        if (node.left != null) {
            levels = levelOrderHelper(node.left, level + 1, levels);
        }

        if (node.right != null) {
            levels = levelOrderHelper(node.right, level + 1, levels);
        }

        return levels;
    }

    /**
     * Solution(Iteratively)
     * Queue: LinkedList
     * https://www.youtube.com/watch?v=1vAjwJz0oac
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder_iterative(TreeNode root) {
        List<List<Integer>> levels = new ArrayList<>();

        // check corner cases
        if (root == null) {
            return levels;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                // remove first node in the queue
                TreeNode node = queue.poll();
                level.add(node.val);

                // add left node to the queue
                if (node.left != null) {
                    queue.add(node.left);
                }

                // add right node to the queue
                if (node.right != null) {
                    queue.add(node.right);
                }
            }

            // add each level to levels
            levels.add(level);
        }

        return levels;
    }

    public static void main(String[] args) {
        System.out.println("102. Binary Tree Level Order Traversal [medium]");

        LC102 solution = new LC102();
        TreeNode tree = new TreeNode(3);
        tree.left = new TreeNode(9);
        tree.right = new TreeNode(20);
        tree.right.left = new TreeNode(15);
        tree.right.right = new TreeNode(7);

        System.out.print("Given binary tree [ ");
        tree.printLevelOrder();
        System.out.println("]");

        List<List<Integer>> output_iterative = solution.levelOrder_iterative(tree);
        List<List<Integer>> output_recursive = solution.levelOrder_recursive(tree);
        System.out.println("Output(Iteration): " + Arrays.deepToString(output_iterative.toArray()));
        System.out.println("Output(Recursion): " + Arrays.deepToString(output_recursive.toArray()));
    }
}
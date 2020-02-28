import java.util.LinkedList;
import java.util.Queue;

public class LC101 {
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
     * Solution(Iterative)
     * DFS
     * https://leetcode.com/problems/symmetric-tree/solution/
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * @param root
     * @return
     */
    public boolean isSymmetric_iterative(TreeNode root) {
        // check corner case
        // if no tree -> symetric
        if (root == null) return true;

        // FIFO
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root.left);
        queue.add(root.right);

        while (!queue.isEmpty()) {
            TreeNode left = queue.poll();
            TreeNode right = queue.poll();

            // Check conditions
            if (left == null && right == null) continue; // continue next iteration
            if (left == null || right == null) return false;
            if (left.val != right.val) return false;

            // update queue for comparing isMirror
            // add oders for next iteration's comparasion
            queue.add(left.left);
            queue.add(right.right);
            queue.add(left.right);
            queue.add(right.left);
        }

        return true;
    }

    /**
     * Solution(Recursive)
     * DFS(Post-Order)
     * https://www.youtube.com/watch?v=K7LyJTWr2yA
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * @param root
     * @return
     */
    public boolean isSymmetric_recursive(TreeNode root) {
        // check corner case
        // if no tree -> symetric
        if (root == null) return true;
        return isSymmetric(root.left, root.right);
    }

    private boolean isSymmetric(TreeNode left, TreeNode right) {
        // Base cases
        if (left == null && right == null) return true;
        if (left == null || right == null) return false;
        if (left.val != right.val) return false;

        // compare if is symmetric
        return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
    }
    
    public static void main(String[] args) {
        System.out.println("101. Symmetric Tree [easy]");
        LC101 solution = new LC101();
        TreeNode[] trees = solution.generateTrees();
        boolean[] answers = {true, false};
        
        for (int i = 0; i < answers.length; i++) {
            TreeNode root = trees[i];
            boolean output_recursive = solution.isSymmetric_recursive(root);
            boolean output_iterative = solution.isSymmetric_iterative(root);
            boolean answer = answers[i];
            boolean score = (output_recursive == answer) && (output_iterative == answer);

            System.out.println("Example: ");
            System.out.print("Input: [ ");
            root.printLevelOrder();
            System.out.print("]\n");
            System.out.println("Output(Recursive): " + output_recursive);
            System.out.println("Output(Iterative): " + output_iterative);
            System.out.println("Answer: " + answer);
            System.out.println("Score: " + score);
            System.out.println();
        }
    }

    private TreeNode[] generateTrees() {
        TreeNode t1 = new TreeNode(1);
        t1.left = new TreeNode(2);
        t1.right = new TreeNode(2);
        t1.left.left = new TreeNode(3);
        t1.left.right = new TreeNode(4);
        t1.right.left = new TreeNode(4);
        t1.right.right = new TreeNode(3);

        TreeNode t2 = new TreeNode(1);
        t2.left = new TreeNode(2);
        t2.right = new TreeNode(2);
        t2.left.right = new TreeNode(3);
        t2.right.right = new TreeNode(3);
        
        return new TreeNode[] {t1, t2};
    }
}
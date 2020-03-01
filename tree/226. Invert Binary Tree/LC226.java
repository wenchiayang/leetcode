import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class LC226 {
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
     * Recursive
     * https://leetcode.com/problems/invert-binary-tree/solution/
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * @param root
     * @return
     */
    public TreeNode invertTree_recursive(TreeNode root) {
        // check base case
        if (root == null) return root;

        // switch left and right references
        TreeNode left = invertTree_recursive(root.left);
        TreeNode right = invertTree_recursive(root.right);

        root.left = right;
        root.right = left;

        return root;
    }

    /**
     * Solution
     * Iterative
     * https://leetcode.com/problems/invert-binary-tree/solution/
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * @param root
     * @return
     */
    public TreeNode invertTree_iterative(TreeNode root) {
        // check corner case
        if (root == null) return root;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            // switch left and right references
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;

            // add remains of left and right to the queue
            if (node.left != null) {
                queue.add(node.left);
            }

            if (node.right != null) {
                queue.add(node.right);
            }
        }

        return root;
    }
    
    public static void main(String[] args) {
        System.out.println("226. Invert Binary Tree [easy]");
        LC226 solution = new LC226();
        TreeNode[] trees = solution.generateTrees();
        int[][] answers = {
            {4, 7, 2, 9, 6, 3, 1}
        };
        
        TreeNode root_recursive = trees[0];
        TreeNode root_iterative = trees[1];
        int[] answer = answers[0];

        System.out.println("Example: ");
        System.out.print("Input(Resursive):  [ ");
        root_recursive.printLevelOrder();
        System.out.print("]\n");
        root_recursive = solution.invertTree_recursive(root_recursive);
        System.out.print("Output(Resursive): [ ");
        root_recursive.printLevelOrder();
        System.out.print("]\n");
        System.out.print("Input(Iterative):  [ ");
        root_iterative.printLevelOrder();
        System.out.print("]\n");
        root_iterative = solution.invertTree_iterative(root_iterative);
        System.out.print("Output(Iterative): [ ");
        root_iterative.printLevelOrder();
        System.out.print("]\n");
        System.out.println("Answer:            " + Arrays.toString(answer).replace(",", ""));
        System.out.println();
    }

    private TreeNode[] generateTrees() {
        TreeNode t1 = new TreeNode(4);
        t1.left = new TreeNode(2);
        t1.right = new TreeNode(7);
        t1.left.left = new TreeNode(1);
        t1.left.right = new TreeNode(3);
        t1.right.left = new TreeNode(6);
        t1.right.right = new TreeNode(9);

        TreeNode t2 = new TreeNode(4);
        t2.left = new TreeNode(2);
        t2.right = new TreeNode(7);
        t2.left.left = new TreeNode(1);
        t2.left.right = new TreeNode(3);
        t2.right.left = new TreeNode(6);
        t2.right.right = new TreeNode(9);
        
        return new TreeNode[] {t1, t2};
    }
}
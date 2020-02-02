import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class LC94 {
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
     * https://leetcode.com/problems/binary-tree-inorder-traversal/solution/
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal_iterative(TreeNode root) {
        List<Integer> paths = new ArrayList<>();

        // check corner cases
        if (root == null) return paths;

        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        
        // LDR
        while (curr != null || !stack.isEmpty()) {
            // L
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }

            // D
            curr = stack.pop();
            paths.add(curr.val);

            // R
            curr = curr.right;
        }

        return paths;
    }

    /**
     * Solution
     * https://leetcode.com/problems/binary-tree-inorder-traversal/solution/
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal_recursive(TreeNode root) {
        return inorder(root);
    }

    private List<Integer> inorder(TreeNode node) {
        List<Integer> paths = new ArrayList<>();

        // base case
        if (node == null) return paths;

        // Recursive call
        List<Integer> left_path = inorder(node.left);
        List<Integer> right_path = inorder(node.right);

        // LDR
        paths.addAll(left_path);
        paths.add(node.val);
        paths.addAll(right_path);

        return paths;
    }

    public static void main(String[] args) {
        System.out.println("94. Binary Tree Inorder Traversal [medium]");
        LC94 solution = new LC94();
        TreeNode root = solution.generateTree();
        int[] answer = {4, 2, 5, 1, 6, 3};
        List<Integer> output_iterative = solution.inorderTraversal_iterative(root);
        List<Integer> output_recursive = solution.inorderTraversal_recursive(root);

        System.out.println("Example: ");
        System.out.print("Input: [ ");
        root.printLevelOrder();
        System.out.print("]\n");
        System.out.println("Output(Iterative): " + Arrays.toString(output_iterative.toArray()));
        System.out.println("Output(Recursive): " + Arrays.toString(output_recursive.toArray()));
        System.out.println("Answer: " + Arrays.toString(answer));
    }

    private TreeNode generateTree() {
        TreeNode t = new TreeNode(1);
        t.left = new TreeNode(2);
        t.right = new TreeNode(3);
        t.left.left = new TreeNode(4);
        t.left.right = new TreeNode(5);
        t.right.left = new TreeNode(6);
        return t;
    }
}
import java.util.List;
import java.util.Stack;
import java.util.ArrayList;
import java.util.Arrays;

public class LC144 {
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
     * DFS
     * https://leetcode.com/problems/binary-tree-preorder-traversal/solution/
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * @param root
     * @return
     */

    public List<Integer> preorderTraversal_recursive(TreeNode root) {
        return preorder(root);
    }

    private List<Integer> preorder(TreeNode node) {
        List<Integer> paths = new ArrayList<>();

        // base case
        if (node == null) return new ArrayList<>();

        // Recursive call
        List<Integer> left_paths = preorderTraversal_recursive(node.left);
        List<Integer> right_paths = preorderTraversal_recursive(node.right);
        
        // DLR
        paths.add(node.val);
        paths.addAll(left_paths);
        paths.addAll(right_paths);

        return paths;
    }

    /**
     * Solution
     * DFS
     * http://www.goodtecher.com/leetcode-144-binary-tree-preorder-traversal/
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal_iterative(TreeNode root) {
        List<Integer> paths = new ArrayList<>();
        if (root == null) return paths;

        // preorder: DLR
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();

            // DLR
            paths.add(node.val);

            // Notice: right before left
            // stack is LIFO
            // so put right first and left last
            // next round when poping out node
            // left node will pop out first
            if (node.right != null) {
                stack.push(node.right);
            }

            if (node.left != null) {
                stack.push(node.left);
            }
        }

        return paths;
    }

    public static void main(String[] args) {
        System.out.println("144. Binary Tree Preorder Traversal [medium]");
        LC144 solution = new LC144();
        TreeNode root = solution.generateTree();
        int[] answer = {1, 2, 3};
        List<Integer> output_iterative = solution.preorderTraversal_iterative(root);
        List<Integer> output_recursive = solution.preorderTraversal_recursive(root);

        System.out.println("Example: ");
        System.out.print("Input: [ ");
        root.printLevelOrder();
        System.out.print("]\n");
        System.out.println("Output(Iterative): " + Arrays.toString(output_iterative.toArray()));
        System.out.println("Output(Recursive): " + Arrays.toString(output_recursive.toArray()));
        System.out.println("Answer: " + Arrays.toString(answer));
    }

    private TreeNode generateTree() {
        TreeNode t1 = new TreeNode(1);
        t1.right = new TreeNode(2);
        t1.right.left = new TreeNode(3);
        return t1;
    }
}
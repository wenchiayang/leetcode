import java.util.Stack;

public class LC98 {
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
     * Inorder(LDR) BST should be sorted
     * https://www.youtube.com/watch?v=Jq0Wk9xeQ0U
     * Time Complexity: O(n)
     * Space Complexity: O(h), tree height
     */
    private TreeNode prev = null;
    public boolean isValidBST_inorder_recursive(TreeNode root) {
        // check corner case
        if (root == null) return true;
        return isValidBST_inorder(root);
    }

    private boolean isValidBST_inorder(TreeNode node) {
        // base case
        if (node == null) return true;

        // check left
        if (!isValidBST_inorder(node.left)) {
            return false;
        }

        // check data
        if (prev != null && prev.val >= node.val) {
            return false;
        }

        prev = node;
        
        // check right
        return isValidBST_inorder(node.right);
    }

    public boolean isValidBST_inorder_iterative(TreeNode root) {
        // check corner cases
        if (root == null) return true;

        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        TreeNode prev = null;

        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }

            curr = stack.pop();

            if (prev != null && prev.val >= curr.val) {
                return false;
            }

            prev = curr;
            curr = curr.right;
        }

        return true;
    }

    /**
     * Solution
     * Recursion
     * https://www.youtube.com/watch?v=DYNiCaKHneY
     * Time Complexity: O(n)
     * Space Complexity: O(h), tree height
     */
    public boolean isValidBST_recursive(TreeNode root) {
        // check corner case
        if (root == null) return true;
        return isBST(root, null, null);
    }

    private boolean isBST(TreeNode node, Integer min, Integer max) {
        // base case
        if (node == null) return true;

        // not BST
        // use null as minimum and maximum range
        if ((min != null && node.val <= min) || (max != null && node.val >= max)) return false;

        // check left & right
        // left:  min < X < root.val
        // right: root.val < X < max
        boolean isLeftBST = isBST(node.left, min, node.val);
        boolean isRightBST = isBST(node.right, node.val, max);

        return isLeftBST && isRightBST;
    }

    public static void main(String[] args) {
        System.out.println("98. Validate Binary Search Tree [medium]");
        LC98 solution = new LC98();
        TreeNode[] inputs = solution.generateTrees();
        boolean[] answers = {true, false};

        for (int i = 0; i < inputs.length; i++) {
            System.out.println("Example " + (i + 1) + ":");
            TreeNode root = inputs[i];
            System.out.print("Input: [ ");
            root.printLevelOrder();
            System.out.print("]\n");
            boolean output_recursive = solution.isValidBST_recursive(root);
            boolean output_inorder_iterative = solution.isValidBST_inorder_iterative(root);
            boolean output_inorder_recursive = solution.isValidBST_inorder_recursive(root);
            boolean answer = answers[i];
            System.out.println("Output(Recursive): " + output_recursive);
            System.out.println("Output(Inorder Iterative): " + output_inorder_iterative);
            System.out.println("Output(Inorder Recursive): " + output_inorder_recursive);
            System.out.println("Answer: " + answer);
            System.out.println("Score(Recursive): " + (output_recursive == answer));
            System.out.println("Score(Inorder Iterative): " + (output_inorder_iterative == answer));
            System.out.println("Score(Inorder Recursive): " + (output_inorder_recursive == answer));
            System.out.println();
        }
    }

    private TreeNode[] generateTrees() {
        TreeNode t1 = new TreeNode(2);
        t1.left = new TreeNode(1);
        t1.right = new TreeNode(3);

        TreeNode t2 = new TreeNode(5);
        t2.left = new TreeNode(1);
        t2.right = new TreeNode(4);
        t2.right.left = new TreeNode(3);
        t2.right.right = new TreeNode(6);

        return new TreeNode[] {t1, t2};
    }
    
}
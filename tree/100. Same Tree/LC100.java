/**
 * LC100
 */
public class LC100 {
    // Recursion
    // Time Complexity: O(n)
    // Space Complecity: 
    // Best Case -> O(logn), where the tree is a completely balanced tree
    // Worst Case -> O(n), where the tree is a completely unbalanced tree
    public boolean isSameTree(TreeNode p, TreeNode q) {
        // Both trees are empty
        if (p == null && q == null) {
            return true;
        }

        // One of trees is empty
        if (p == null || q == null) {
            return false;
        }

        // Both trees are not empty, compare values
        if (p.val != q.val) {
            return false;
        }

        // Compare left and right subtrees recursively
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
    
    public static void main(String[] args) {
        LC100 solution = new LC100();

        TreeNode t1a = new TreeNode(1);
        t1a.left = new TreeNode(2);
        t1a.right = new TreeNode(3);

        TreeNode t1b = new TreeNode(1);
        t1b.left = new TreeNode(2);
        t1b.right = new TreeNode(3);

        TreeNode t2a = new TreeNode(1);
        t2a.left = new TreeNode(2);

        TreeNode t2b = new TreeNode(1);
        t2b.right = new TreeNode(2);

        TreeNode t3a = new TreeNode(1);
        t3a.left = new TreeNode(2);
        t3a.right = new TreeNode(1);

        TreeNode t3b = new TreeNode(1);
        t3b.left = new TreeNode(1);
        t3b.right = new TreeNode(2);

        TreeNode[][] inputs = {
            {t1a, t1b}, {t2a, t2b}, {t3a, t3b}
        };

        for (int i = 0; i < inputs.length; i++) {
            System.out.println("Example: " + (i + 1));
            System.out.print("Tree 0: [ ");
            inputs[i][0].printLevelOrder();
            System.out.print("]\n");
            System.out.print("Tree 1: [ ");
            inputs[i][1].printLevelOrder();
            System.out.print("]\n");
            System.out.println("Output: " + solution.isSameTree(inputs[i][0], inputs[i][1]));
            System.out.println();
        }
    }
}
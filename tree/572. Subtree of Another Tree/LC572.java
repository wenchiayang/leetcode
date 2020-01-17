/**
 * LC572
 */
public class LC572 {
    // Recursion
    // Time complexity : O(m*n). In worst case(skewed tree) traverse function takes O(m*n) time.
    // Space complexity : O(n). The depth of the recursion tree can go upto n. n refers to the number of nodes in s.
    public boolean isSubtree(TreeNode s, TreeNode t) {
        return traverse(s, t);
    }

    private boolean traverse(TreeNode s, TreeNode t) {
        // check edge cases
        if (s == null) {
            return false;
        }

        // check if s and t are the same trees
        if (isSameTree(s, t)) {
            return true;
        }

        // recursively compare left and rigth subtree of s and t
        return isSubtree(s.left, t) || isSubtree(s.right, t);
    }

    private boolean isSameTree(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return true;
        }

        if (t1 == null || t2 == null) {
            return false;
        }

        if (t1.val != t2.val) {
            return false;
        }

        return isSameTree(t1.left, t2.left) && isSameTree(t1.right, t2.right);
    }

    public static void main(String[] args) {
        LC572 solution = new LC572();

        TreeNode t1a = new TreeNode(3);
        t1a.left = new TreeNode(4);
        t1a.right = new TreeNode(5);
        t1a.left.left = new TreeNode(1);
        t1a.left.right = new TreeNode(2);

        TreeNode t1b = new TreeNode(4);
        t1b.left = new TreeNode(1);
        t1b.right = new TreeNode(2);

        TreeNode t2a = new TreeNode(3);
        t2a.left = new TreeNode(4);
        t2a.right = new TreeNode(5);
        t2a.left.left = new TreeNode(1);
        t2a.left.right = new TreeNode(2);
        t2a.left.right.left = new TreeNode(0);

        TreeNode t2b = new TreeNode(4);
        t2b.left = new TreeNode(1);
        t2b.right = new TreeNode(2);

        TreeNode[][] inputs = {
            {t1a, t1b}, {t2a, t2b}
        };

        for (int i = 0; i < inputs.length; i++) {
            System.out.println("Example: " + (i + 1));
            System.out.print("Tree 0: [ ");
            inputs[i][0].printLevelOrder();
            System.out.print("]\n");
            System.out.print("Tree 1: [ ");
            inputs[i][1].printLevelOrder();
            System.out.print("]\n");
            System.out.println("Output: " + solution.isSubtree(inputs[i][0], inputs[i][1]));
            System.out.println();
        }
    }
}
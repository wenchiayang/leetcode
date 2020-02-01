import java.util.Stack;

public class LC617 {
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
     * https://leetcode.com/problems/merge-two-binary-trees/solution/
     * Time Complexity: O(n). We traverse over a total of n nodes. 
     * Here, n refers to the smaller of the number of nodes in the two trees.
     * Space Complexity: O(n). The depth of stack can grow upto nn in case of a skewed tree.
     * @param t1
     * @param t2
     * @return
     */
    public TreeNode mergeTrees_iterative(TreeNode t1, TreeNode t2) {
        if (t1 == null) return t2;

        Stack<TreeNode[]> stack = new Stack<>();
        stack.push(new TreeNode[] {t1, t2});

        while (!stack.isEmpty()) {
            TreeNode[] t = stack.pop();

            // if t[0] or t[1] is null, skip and go next round
            if (t[0] == null || t[1] == null) continue;

            // add value
            t[0].val += t[1].val;

            // combine trees
            if (t[0].left == null) {
                t[0].left = t[1].left;
            } else {
                stack.push(new TreeNode[] {t[0].left, t[1].left});
            }

            if (t[0].right == null) {
                t[0].right = t[1].right;
            } else {
                stack.push(new TreeNode[] {t[0].right, t[1].right});
            }
        }

        return t1;
    }

    /**
     * Solution
     * Recursive(DFS)
     * https://www.youtube.com/watch?v=_LJO5nBKC1A
     * Time Complexity: O(m). A total of m nodes need to be traversed. 
     * Here, m represents the min number of nodes from the two given trees.
     * Space Complexity: O(m). The depth of the recursion tree can go upto m in the case of a skewed tree. 
     * In average case, depth will be O(logm).
     * @param t1
     * @param t2
     * @return
     */
    public TreeNode mergeTrees_recursive(TreeNode t1, TreeNode t2) {
        // base cases
        if (t1 == null) return t2;
        if (t2 == null) return t1;

        // add t2 to t1
        t1.val += t2.val;

        // recursive call
        t1.left = mergeTrees_recursive(t1.left, t2.left);
        t1.right = mergeTrees_recursive(t1.right, t2.right);

        return t1;
    }

    public static void main(String[] args) {
        System.out.println("617. Merge Two Binary Trees [easy]");
        LC617 solution = new LC617();

        for (int i = 0; i < 2; i++) {
            TreeNode[] samples = solution.generateTrees(i);
            samples = solution.generateTrees2();
            TreeNode t1 = samples[0];
            TreeNode t2 = samples[1];
            TreeNode answer = samples[2];

            System.out.println("Example" + (i + 1) + ":");
            System.out.println("Input:");
            System.out.print("Tree 1: [ ");
            t1.printLevelOrder();
            System.out.print("]\n");
            System.out.print("Tree 2: [ ");
            t2.printLevelOrder();
            System.out.print("]\n");
            System.out.println("Output:");
            TreeNode output;

            if (i == 0) {
                System.out.println("mergeTrees_recursive");
                output = solution.mergeTrees_recursive(t1, t2);
                System.out.print("Merged tree: [ ");
                output.printLevelOrder();
                System.out.print("]\n");
                System.out.print("Answer     : [ ");
                answer.printLevelOrder();
                System.out.print("]");
            } else {
                System.out.println("mergeTrees_iterative");
                output = solution.mergeTrees_iterative(t1, t2);
                System.out.print("Merged tree: [ ");
                output.printLevelOrder();
                System.out.print("]\n");
                System.out.print("Answer     : [ ");
                answer.printLevelOrder();
                System.out.print("]");
            }

            System.out.println();
        }
    }

    private TreeNode[] generateTrees(int rootValue) {
        TreeNode t1 = new TreeNode(rootValue);
        t1.left = new TreeNode(3);
        t1.right = new TreeNode(2);
        t1.left.left = new TreeNode(5);

        TreeNode t2 = new TreeNode(rootValue);
        t2.left = new TreeNode(1);
        t2.right = new TreeNode(3);
        t2.left.right = new TreeNode(4);
        t2.right.right = new TreeNode(7);

        TreeNode answer = new TreeNode(rootValue * 2);
        answer.left = new TreeNode(4);
        answer.right = new TreeNode(5);
        answer.left.left = new TreeNode(5);
        answer.left.right = new TreeNode(4);
        answer.right.right = new TreeNode(7);

        return new TreeNode[] {t1, t2, answer};
    }

    private TreeNode[] generateTrees2() {
        TreeNode t1 = new TreeNode(1);
        t1.left = new TreeNode(2);
        t1.right = new TreeNode(3);
        t1.left.left = new TreeNode(4);
        t1.left.right = new TreeNode(5);

        TreeNode t2 = new TreeNode(5);
        t2.left = new TreeNode(3);
        t2.right = new TreeNode(6);
        t2.left.left = new TreeNode(2);
        t2.right.left = new TreeNode(1);
        t2.right.right = new TreeNode(4);

        TreeNode answer = new TreeNode(6);
        answer.left = new TreeNode(5);
        answer.right = new TreeNode(9);
        answer.left.left = new TreeNode(6);
        answer.left.right = new TreeNode(5);
        answer.right.left = new TreeNode(1);
        answer.right.right = new TreeNode(4);

        return new TreeNode[] {t1, t2, answer};
    }
}
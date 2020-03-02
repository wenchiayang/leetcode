import java.util.Stack;

public class LC112 {
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
     * https://www.youtube.com/watch?v=2PjOR354ASs
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * @param root
     * @param sum
     * @return
     */
    public boolean hasPathSum_recursive(TreeNode root, int sum) {
        // check corner case
        if (root == null) return false;

        // if is leaf node, check if leaf.val == current remain sum
        if (root.left == null && root.right == null && root.val == sum) {
            return true;
        }

        // check left or right node
        return hasPathSum_recursive(root.left, sum - root.val) || hasPathSum_recursive(root.right, sum - root.val);
    }

    /**
     * Solution
     * Iterative
     * https://leetcode.com/problems/path-sum/solution/
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * @param root
     * @param sum
     * @return
     */
    public boolean hasPathSum_iterative(TreeNode root, int sum) {
        // check corner case
        if (root == null) return false;
        
        Stack<TreeNode> nodeStack = new Stack<>();
        Stack<Integer> remainingSumStack = new Stack<>();
        nodeStack.push(root);
        remainingSumStack.push(sum - root.val);

        while (!nodeStack.isEmpty()) {
            TreeNode node = nodeStack.pop();
            int remainingSum = remainingSumStack.pop();

            // if is leaf node, check if remaining sum == 0
            if (node.left == null && node.right == null && remainingSum == 0) {
                return true;
            }

            // check left node
            if (node.left != null) {
                nodeStack.push(node.left);
                remainingSumStack.push(remainingSum - node.left.val);
            }

            // check right node
            if (node.right != null) {
                nodeStack.push(node.right);
                remainingSumStack.push(remainingSum - node.right.val);
            }
        }
        
        return false;
    }
    
    public static void main(String[] args) {
        System.out.println("112. Path Sum [easy]");
        LC112 solution = new LC112();
        TreeNode[] trees = solution.generateTrees();
        int[] sums = {22};
        boolean[] answers = {true};
        
        for (int i = 0; i < answers.length; i++) {
            TreeNode root = trees[0];
            int sum = sums[0];
            boolean output_recursive = solution.hasPathSum_recursive(root, sum);
            boolean output_iterative = solution.hasPathSum_iterative(root, sum);
            boolean answer = answers[0];
            boolean score = (output_recursive == answer) && (output_iterative == answer);

            System.out.println("Example: ");
            System.out.print("Input: [ ");
            root.printLevelOrder();
            System.out.print("], sum = " + sum + "\n");
            System.out.println("Output(recursive): " + output_recursive);
            System.out.println("Output(iterative): " + output_iterative);
            System.out.println("Answer: " + answer);
            System.out.println("Score: " + score);
            System.out.println();
        }
    }

    private TreeNode[] generateTrees() {
        TreeNode t1 = new TreeNode(5);
        t1.left = new TreeNode(4);
        t1.right = new TreeNode(8);
        t1.left.left = new TreeNode(11);
        t1.right.left = new TreeNode(13);
        t1.right.right = new TreeNode(4);
        t1.left.left.left = new TreeNode(7);
        t1.left.left.right = new TreeNode(2);
        t1.right.right.right = new TreeNode(1);
        return new TreeNode[] {t1};
    }
}
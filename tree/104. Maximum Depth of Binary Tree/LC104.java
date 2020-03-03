import java.util.LinkedList;
import java.util.Stack;

public class LC104 {
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
     * Solution
     * https://www.youtube.com/watch?v=D2cFSDfg0So
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * @param root
     * @return
     */
    public int maxDepth_recursive(TreeNode root) {
        // check corner case
        if (root == null) {
            return 0;
        }

        return Math.max(maxDepth_recursive(root.left), maxDepth_recursive(root.right)) + 1;
    }

    /**
     * Solution
     * https://leetcode.com/problems/maximum-depth-of-binary-tree/solution/
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * @param root
     * @return
     */
    public int maxDepth_dfs(TreeNode root) {
        // check corner case
        if (root == null) {
            return 0;
        }

        Stack<TreeNode> nodeStack = new Stack<>();
        Stack<Integer> depthStack = new Stack<>();

        nodeStack.push(root);
        depthStack.push(1);
        int maxDepth = 0;

        while (!nodeStack.isEmpty()) {
            TreeNode node = nodeStack.pop();
            int currentDepth = depthStack.pop();

            if (node == null) {
                continue;
            }

            // update depth
            maxDepth = Math.max(maxDepth, currentDepth);
            
            // iterate left
            nodeStack.push(node.left);
            depthStack.push(currentDepth + 1);

            // iterate right
            nodeStack.push(node.right);
            depthStack.push(currentDepth + 1);
        }

        return maxDepth;
    }
    
    public static void main(String[] args) {
        System.out.println("104. Maximum Depth of Binary Tree [easy]");
        LC104 solution = new LC104();
        TreeNode[] trees = solution.generateTrees();
        int[] answers = {3};
        
        for (int i = 0; i < answers.length; i++) {
            TreeNode root = trees[i];
            int output_recursion = solution.maxDepth_recursive(root);
            int output_dfs = solution.maxDepth_dfs(root);
            int answer = answers[0];
            boolean score = (output_recursion == answer) && (output_dfs == answer);

            System.out.println("Example: ");
            System.out.print("Input: [ ");
            root.printLevelOrder();
            System.out.print("]\n");
            System.out.println("Output(Recursion): " + output_recursion);
            System.out.println("Output(DFS): " + output_dfs);
            System.out.println("Answer: " + answer);
            System.out.println("Score: " + score);
            System.out.println();
        }
    }

    private TreeNode[] generateTrees() {
        TreeNode t1 = new TreeNode(3);
        t1.left = new TreeNode(9);
        t1.right = new TreeNode(20);
        t1.right.left = new TreeNode(15);
        t1.right.right = new TreeNode(7);
        
        return new TreeNode[] {t1};
    }
}
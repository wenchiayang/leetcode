
public class LC543 {
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
                    return (lheight + 1); 
                } else {
                    return (rheight + 1);  
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
     * DFS, calculate depth(height)
     * https://www.youtube.com/watch?v=0VnOfu2pYTo
     * Time Complexity: O(n)
     * Space Complexity: O(h), where h is the height of the tree
     * @param root
     * @return
     */
    private int diameter = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        // check corner cases
        if (root == null) return 0; 

        // DFS
        depth(root);
        return diameter;
    }

    private int depth(TreeNode root) {
        // Base case
        if (root == null) return 0;

        // recursive call
        int left = depth(root.left);
        int right = depth(root.right);

        // update diameter
        // it's possible that the longest diameter is lonly on one side of the tree
        diameter = Math.max(diameter, left + right);

        // Find Math of left and right + root as the depth(height) of the tree
        return Math.max(left, right) + 1;
    }

    public static void main(String[] args) {
        System.out.println("543. Diameter of Binary Tree [easy]");
        LC543 solution = new LC543();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        int answer = 3;
        int output = solution.diameterOfBinaryTree(root);

        System.out.println("Example:");
        System.out.print("Given a binary tree: [");
        root.printLevelOrder();
        System.out.print("]\n");
        System.out.println("Output: " + output);
        System.out.println("Answer: " + answer);
        System.out.println("Score: " + (answer == output));
    }
}
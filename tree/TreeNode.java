/**
 * TreeNode
 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }

    /* function to print level order traversal of tree*/
    void printLevelOrder() 
    { 
        int h = height(this); 
        int i; 
        for (i=1; i<=h; i++) 
            printGivenLevel(this, i); 
    } 
  
    /* Compute the "height" of a tree -- the number of 
    nodes along the longest path from the root node 
    down to the farthest leaf node.*/
    int height(TreeNode root) 
    { 
        if (root == null) 
           return 0; 
        else
        { 
            /* compute  height of each subtree */
            int lheight = height(root.left); 
            int rheight = height(root.right); 
              
            /* use the larger one */
            if (lheight > rheight) 
                return(lheight+1); 
            else return(rheight+1);  
        } 
    } 
  
    /* Print nodes at the given level */
    void printGivenLevel (TreeNode root ,int level) 
    { 
        if (root == null) {
            System.out.print("null ");
            return; 
        }
        if (level == 1) 
            System.out.print(root.val + " "); 
        else if (level > 1) 
        { 
            printGivenLevel(root.left, level-1); 
            printGivenLevel(root.right, level-1); 
        } 
    } 
}
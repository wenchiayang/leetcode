import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LC107 {
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
     * DFS
     * https://leetcode.com/problems/binary-tree-level-order-traversal-ii/discuss/34981/My-DFS-and-BFS-java-solution
     * Time Complexity: O(n)
     * Space Complexity: O(h), where h is the height of the tree
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderBottom_dfs(TreeNode root) {
        List<List<Integer>> levels = new ArrayList<>();

        // check corner case
        if (root == null) {
            return levels;
        }

        levels = dfs_helper(root, 0, levels);
        return levels;
    }

    private List<List<Integer>> dfs_helper(TreeNode node, int level, List<List<Integer>> levels) {
        if (node == null) {
            return new ArrayList<>();
        }

        if (level >= levels.size()) {
            levels.add(0, new ArrayList<>());
        }
        
        // add val to current level reversely
        levels.get(levels.size() - level - 1).add(node.val);
        
        if (node.left != null) {
            levels = dfs_helper(node.left, level + 1, levels);
        }

        if (node.right != null) {
            levels = dfs_helper(node.right, level + 1, levels);
        }

        return levels;
    }

    /**
     * Solution
     * BFS
     * https://github.com/awangdev/LintCode/blob/master/Java/Binary%20Tree%20Level%20Order%20Traversal%20II.java
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderBottom_bfs(TreeNode root) {
        ArrayList<List<Integer>> levels = new ArrayList<>();

        // check corner case
        if (root == null) {
            return levels;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> path = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                path.add(node.val);

                if (node.left != null) {
                    queue.add(node.left);
                }

                if (node.right != null) {
                    queue.add(node.right);
                }
            }

            levels.add(0, path);
        }

        return levels;
    }

    public static void main(String[] args) {
        System.out.println("107. Binary Tree Level Order Traversal II [easy]");

        LC107 solution = new LC107();
        TreeNode tree = new TreeNode(3);
        tree.left = new TreeNode(9);
        tree.right = new TreeNode(20);
        tree.right.left = new TreeNode(15);
        tree.right.right = new TreeNode(7);

        System.out.print("Given binary tree [ ");
        tree.printLevelOrder();
        System.out.println("]");

        List<List<Integer>> output_iterative = solution.levelOrderBottom_bfs(tree);
        List<List<Integer>> output_recursive = solution.levelOrderBottom_dfs(tree);
        System.out.println("Output(Iteration: BFS): " + Arrays.deepToString(output_iterative.toArray()));
        System.out.println("Output(Recursion: DFS): " + Arrays.deepToString(output_recursive.toArray()));
    }
}
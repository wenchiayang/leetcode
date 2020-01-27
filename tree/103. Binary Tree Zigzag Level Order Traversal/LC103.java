import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class LC103 {
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
     * Solution(BFS)
     * https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/solution/
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrder_bfs(TreeNode root) {
        List<List<Integer>> levels = new ArrayList<>();

        // check corner cases
        if (root == null) {
            return levels;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int level = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            // List<Integer> path = new ArrayList<>();
            LinkedList<Integer> path = new LinkedList<>();

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                
                // check level, if odd, reverse path
                if (level % 2 == 0) {
                    path.addLast(node.val); // add val to path
                } else {
                    path.addFirst(node.val); // reversely add val to path
                }

                if (node.left != null) {
                    queue.add(node.left);
                }

                if (node.right != null) {
                    queue.add(node.right);
                }
            }

            levels.add(path);
            level++;
        }

        return levels;
    }

    /**
     * Solution(Recursion)
     * DFS
     * https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/solution/
     * Time Complexity: O(n)
     * Space Complexity: O(h), where h is the height of the tree
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrder_recursive(TreeNode root) {
        List<List<Integer>> levels = new ArrayList<>();

        // check corner cases
        if (root == null) {
            return levels;
        }

        levels = DFS(root, 0, levels);
        return levels;
    }

    private List<List<Integer>> DFS(TreeNode node, int level, List<List<Integer>> levels) {
        if (level >= levels.size()) {
            List<Integer> path = new LinkedList<>();
            path.add(node.val);
            levels.add(path); 
        } else {
            if (level % 2 == 0) {
                // add last
                levels.get(level).add(node.val);
            } else {
                // add first
                levels.get(level).add(0, node.val);
            } 
        }

        if (node.left != null) {
            levels = DFS(node.left, level + 1, levels);
        }

        if (node.right != null) {
            levels = DFS(node.right, level + 1, levels);
        }

        return levels;
    }

    /**
     * Solutoin(Iteration)
     * Reverse path in odd levels
     * https://www.youtube.com/watch?v=NzPlgKJJZvo
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrder_iterative(TreeNode root) {
        List<List<Integer>> levels = new ArrayList<>();

        // check corner cases
        if (root == null) {
            return levels;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int level = 0;

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

            // reverse path when level is odd
            if (level % 2 == 1) {
                Collections.reverse(path);
            }

            levels.add(path);
            level++;
        }

        return levels;
    }

    public static void main(String[] args) {
        System.out.println("102. Binary Tree Level Order Traversal [medium]");

        LC103 solution = new LC103();
        TreeNode tree = new TreeNode(3);
        tree.left = new TreeNode(9);
        tree.right = new TreeNode(20);
        tree.right.left = new TreeNode(15);
        tree.right.right = new TreeNode(7);

        System.out.print("Given binary tree [ ");
        tree.printLevelOrder();
        System.out.println("]");
        List<List<Integer>> output_bfs = solution.zigzagLevelOrder_bfs(tree);
        List<List<Integer>> output_iterative = solution.zigzagLevelOrder_iterative(tree);
        List<List<Integer>> output_recursive = solution.zigzagLevelOrder_recursive(tree);
        System.out.println("Output(BFS): " + Arrays.deepToString(output_bfs.toArray()));
        System.out.println("Output(Iteration): " + Arrays.deepToString(output_iterative.toArray()));
        System.out.println("Output(DFS): " + Arrays.deepToString(output_recursive.toArray()));
    }
}
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class LC111 {
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
     * Recursion
     * https://www.youtube.com/watch?v=WPq3xmA3jBY
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * @param root
     * @return
     */
    public int minDepth_recursive(TreeNode root) {
        // check corner cases
        if (root == null) {
            return 0;
        }

        // root has left and right child nodes
        if (root.left != null && root.right != null) {
            return Math.min(minDepth_recursive(root.left), minDepth_recursive(root.right)) + 1;
        } else { // one of left or right child nodes at root is empty
            return Math.max(minDepth_recursive(root.left), minDepth_recursive(root.right)) + 1;
        }
    }

    /**
     * Solution
     * Iteration(DFS)
     * https://leetcode.com/problems/minimum-depth-of-binary-tree/solution/
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * @param root
     * @return
     */
    public int minDepth_dfs(TreeNode root) {
        // check corner cases
        if (root == null) {
            return 0;
        }

        Stack<TreeNode> nodeStack = new Stack<>();
        Stack<Integer> depthStack = new Stack<>();
        nodeStack.push(root);
        depthStack.push(1);

        int minDepth = Integer.MAX_VALUE;
        while (!nodeStack.isEmpty()) {
            TreeNode node = nodeStack.pop();
            int currentDepth = depthStack.pop();

            // leaf node(x left, x right child nodes)
            if (node.left == null && node.right == null) {
                minDepth = Math.min(minDepth, currentDepth);
            }

            // iterate left child node
            if (node.left != null) {
                nodeStack.push(node.left);
                depthStack.push(currentDepth + 1);
            }

            // iterate right child node
            if (node.right != null) {
                nodeStack.push(node.right);
                depthStack.push(currentDepth + 1);
            }
        }

        return minDepth;
    }

    /**
     * Solution
     * Iteration(BFS)
     * https://leetcode.com/problems/minimum-depth-of-binary-tree/solution/
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * @param root
     * @return
     */
    public int minDepth_bfs(TreeNode root) {
        // check corner cases
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<Integer> depthQueue = new LinkedList<>();
        nodeQueue.add(root);
        depthQueue.add(1);
        int minDepth = 0;

        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.poll();
            minDepth = depthQueue.poll();

            // leaf node(x left, x right child nodes)
            if (node.left == null && node.right == null) {
                break;
            }

            // iterate left child node
            if (node.left != null) {
                nodeQueue.add(node.left);
                depthQueue.add(minDepth + 1);
            }

            // iterate right child node
            if (node.right != null) {
                nodeQueue.add(node.right);
                depthQueue.add(minDepth + 1);
            }
        }

        return minDepth;
    }
    
    public static void main(String[] args) {
        System.out.println("687. Longest Univalue Path [easy]");
        LC111 solution = new LC111();
        TreeNode[] trees = solution.generateTrees();
        int[] answers = {2};
        
        for (int i = 0; i < answers.length; i++) {
            TreeNode root = trees[i];
            int output_recursion = solution.minDepth_recursive(root);
            int output_dfs = solution.minDepth_dfs(root);
            int output_bfs = solution.minDepth_bfs(root);
            int answer = answers[0];
            boolean score = (output_recursion == answer) && (output_dfs == answer) && (output_bfs == answer);

            System.out.println("Example: ");
            System.out.print("Input: [ ");
            root.printLevelOrder();
            System.out.print("]\n");
            System.out.println("Output(Recursion): " + output_recursion);
            System.out.println("Output(DFS): " + output_dfs);
            System.out.println("Output(BFS): " + output_bfs);
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
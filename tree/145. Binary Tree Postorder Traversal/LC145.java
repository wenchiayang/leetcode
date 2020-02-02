import java.util.List;
import java.util.Stack;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class LC145 {
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
     * reverse postorder + linkedlist add first
     * https://www.youtube.com/watch?v=5oX6KpQfwZc
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal_iterative2(TreeNode root) {
        LinkedList<Integer> paths = new LinkedList<>();

        // check edge cases
        if (root == null) return paths;
        
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        // LRD
        // LinkedList add first from D
        // stack add L -> R, pop R -> L
        // LinkedList -> LRD
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            paths.addFirst(node.val);
            
            if (node.left != null) {
                stack.push(node.left);
            }

            if (node.right != null) {
                stack.push(node.right);
            }
        }

        return paths;
    }

    /**
     * Solution
     * https://www.youtube.com/watch?v=5oX6KpQfwZc
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal_iterative1(TreeNode root) {
        List<Integer> paths = new ArrayList<>();

        // check edge cases
        if (root == null) return paths;
        
        // LRD
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        TreeNode curr = root;

        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                stack.push(curr);
                curr = curr.left;
            } else {
                curr = stack.peek().right;

                if (curr == null || curr == pre) {
                    pre = stack.pop();
                    paths.add(pre.val);
                    curr = null;
                }
            }
        }

        return paths;
    }

    /**
     * Solution
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal_recursive(TreeNode root) {
        return postorder(root);
    }

    private List<Integer> postorder(TreeNode node) {
        List<Integer> paths = new ArrayList<>();

        // base case
        if (node == null) return paths;

        List<Integer> left_path = postorder(node.left);
        List<Integer> right_path = postorder(node.right);

        // LRD
        paths.addAll(left_path);
        paths.addAll(right_path);
        paths.add(node.val);

        return paths;
    }

    public static void main(String[] args) {
        System.out.println("145. Binary Tree Postorder Traversal [hard]");
        LC145 solution = new LC145();
        TreeNode root = solution.generateTree();
        int[] answer = {3, 2, 1};
        List<Integer> output_iterative1 = solution.postorderTraversal_iterative1(root);
        List<Integer> output_iterative2 = solution.postorderTraversal_iterative2(root);
        List<Integer> output_recursive = solution.postorderTraversal_recursive(root);

        System.out.println("Example: ");
        System.out.print("Input: [ ");
        root.printLevelOrder();
        System.out.print("]\n");
        System.out.println("Output(Iterative1): " + Arrays.toString(output_iterative1.toArray()));
        System.out.println("Output(Iterative2): " + Arrays.toString(output_iterative2.toArray()));
        System.out.println("Output(Recursive): " + Arrays.toString(output_recursive.toArray()));
        System.out.println("Answer: " + Arrays.toString(answer));
    }

    private TreeNode generateTree() {
        TreeNode t = new TreeNode(1);
        t.left = new TreeNode(2);
        t.right = new TreeNode(3);
        t.left.left = new TreeNode(4);
        t.left.right = new TreeNode(5);
        t.right.left = new TreeNode(6);
        return t;
    }
}
import java.util.Stack;

public class LC426 {
    static class Node {
        public int val;
        public Node left;
        public Node right;
    
        public Node() {}
    
        public Node(int _val) {
            val = _val;
        }
    
        public Node(int _val,Node _left,Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }

        void printNode() {
            Node curr = this;
            Node head = this;

            while (true) {
                if (curr.right != head) {
                    System.out.print(curr.val + " -> ");
                } else {
                    System.out.print(curr.val);
                    break;
                }
                
                curr = curr.right;
            }
        }

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
        int height(Node root) { 
            if (root == null) return 0;     
            /* compute  height of each subtree */
            int left = height(root.left); 
            int right = height(root.right); 
                
            /* use the larger one */
            return Math.max(left, right) + 1;
        } 
      
        /* Print nodes at the given level */
        void printGivenLevel (Node root ,int level) { 
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
     * Inorder Recursive
     * https://leetcode.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/solution/
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * @param root
     * @return
     */
    private Node head = null;
    private Node prev = null;
    public Node treeToDoublyList(Node root) {
        // check corner case
        if (root == null) return null;

        // inorder: LDR
        inorder(root);

        //close DLL
        prev.right = head;
        head.left = prev;
        
        return head;
    }

    private void inorder(Node node) {
        // base case
        if (node == null) return;

        // L
        inorder(node.left);

        // D
        if (prev == null) {
            // keep the smallest node
            // to close DLL later on
            head = node;
        } else {
            // link the previous node (last)
            // with the current one (node)
            prev.right = node;
            node.left = prev;
        }

        prev = node;

        // R
        inorder(node.right);
    }

    /**
     * Solution
     * Inorder Iterative
     * https://www.youtube.com/watch?v=oY7d3lsC3Ew
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * @param root
     * @return
     */
    public Node treeToDoublyList2(Node root) {
        // check corner case
        if (root == null) return null;

        // for doubly linked list
        Node prev = null;
        Node head = null;

        // for tree inorder(LDR) traverse
        Node curr = root;
        Stack<Node> stack = new Stack<>();

        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }

            curr = stack.pop();

            // construct d-list
            if (prev == null) {
                head = curr;
            } else {
                // link prev and next node
                prev.right = curr;
                curr.left = prev;
            }

            prev = curr; // move prev to next
            curr = curr.right;
        }

        // connect head and tail
        prev.right = head;
        head.left = prev;

        return head;
    }

    public static void main(String[] args) {
        System.out.println("426. Convert Binary Search Tree to Sorted Doubly Linked List [medium]");
        LC426 solution = new LC426();
        Node[] roots = solution.generateTrees();
        String[] answers = {
            "[1,2,3,4,5]", "[1,2,3]", "[]", "[1]"
        };

        for (int i = 0; i < roots.length; i++) {
            Node root = roots[i];
            String answer = answers[i];
            System.out.println("Example " + (i + 1) + ":");
            System.out.print("Input: root = [ ");
            root.printLevelOrder();
            System.out.print("]\n");
            System.out.print("Output: ");
            Node output = solution.treeToDoublyList(root);
            output.printNode();
            System.out.println();
            System.out.println("Answer: " + answer);
            System.out.println();
            solution.prev = null;
            solution.head = null;
        }
    }

    private Node[] generateTrees() {
        Node t1 = new Node(4);
        t1.left = new Node(2);
        t1.right = new Node(5);
        t1.left.left = new Node(1);
        t1.left.right = new Node(3);

        Node t2 = new Node(2);
        t2.left = new Node(1);
        t2.right = new Node(3);

        Node t3 = new Node();
        Node t4 = new Node(1);

        return new Node[] {t1, t2, t3, t4};
    }
}
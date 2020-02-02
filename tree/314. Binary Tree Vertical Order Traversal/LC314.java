import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.ArrayList;;

public class LC314 {
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
     * Helper class
     */
    static class Element {
        TreeNode node;
        int column;

        Element(TreeNode node, int column) {
            this.node = node;
            this.column = column;
        }
    }

    /**
     * Solution
     * BFS(Top -> bottom, Left -> right)
     * https://www.bilibili.com/video/av58557047/
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * @param root
     * @return
     */
    public List<List<Integer>> verticalOrder(TreeNode root) {
        // step1: check corner cases
        // step2: BFS(top->bottom, left->right)
        //        root = column 0, left: -, right: +
        //        map: store(column & nodes)
        // step3: construct answers from left(min) to right(max)

        List<List<Integer>> levels = new ArrayList<>();
        
        // check corner case
        if (root == null) return levels;
        
        // store left-most column and right-most column
        // root column = 0, move left -> negative, move right -> positive
        int min = 0, max = 0;

        // map to store (k,v) = (index of column, elements of that column)
        Map<Integer, List<Integer>> counter = new HashMap<>();

        // Queue for BFS
        Queue<Element> queue = new LinkedList<>();
        queue.offer(new Element(root, 0));

        while (!queue.isEmpty()) {
            Element curr = queue.poll();
            
            // update min, max
            min = Math.min(min, curr.column);
            max = Math.max(max, curr.column);

            // update counter
            // check if map contains key: curr.column
            // if not -> create an empty ArrayList
            // if yes -> add new val
            if (!counter.containsKey(curr.column)) {
                counter.put(curr.column, new ArrayList<>());
            }

            counter.get(curr.column).add(curr.node.val);

            // add left & right childs to the queue
            if (curr.node.left != null) {
                // left -> column - 1
                queue.offer(new Element(curr.node.left, curr.column - 1));
            }

            if (curr.node.right != null) {
                // right -> column + 1
                queue.offer(new Element(curr.node.right, curr.column + 1));
            }
        }

        // add result from min to max
        for (int i = min; i <= max; i++) {
            List<Integer> list = counter.get(i);

            if (list != null) {
                levels.add(list);
            }
        }

        return levels;
    }

    public static void main(String[] args) {
        System.out.println("314. Binary Tree Vertical Order Traversal [medium]");
        LC314 solution = new LC314();
        TreeNode[] inputs = solution.generateTrees();
        int[][][] answers = {
            {{9}, {3, 15}, {20}, {7}},
            {{4}, {9}, {3, 0, 1}, {8}, {7}},
            {{4}, {9, 5}, {3, 0, 1}, {8, 2}, {7}}
        };

        for (int i = 0; i < inputs.length; i++) {
            TreeNode root = inputs[i];
            int[][] answer = answers[i];
            List<List<Integer>> output = solution.verticalOrder(root);

            System.out.println("Example" + (i + 1) + ":");
            System.out.print("Input: [ ");
            root.printLevelOrder();
            System.out.print("]\n");
            System.out.println("Output: " + Arrays.deepToString(output.toArray()));
            System.out.println("Answer: " + Arrays.deepToString(answer));
            System.out.println();
        }
    }

    private TreeNode[] generateTrees() {
        TreeNode t1 = new TreeNode(3);
        t1.left = new TreeNode(9);
        t1.right = new TreeNode(20);
        t1.right.left = new TreeNode(15);
        t1.right.right = new TreeNode(7);

        TreeNode t2 = new TreeNode(3);
        t2.left = new TreeNode(9);
        t2.right = new TreeNode(8);
        t2.left.left = new TreeNode(4);
        t2.left.right = new TreeNode(0);
        t2.right.left = new TreeNode(1);
        t2.right.right = new TreeNode(7);

        TreeNode t3 = new TreeNode(3);
        t3.left = new TreeNode(9);
        t3.right = new TreeNode(8);
        t3.left.left = new TreeNode(4);
        t3.left.right = new TreeNode(0);
        t3.right.left = new TreeNode(1);
        t3.right.right = new TreeNode(7);
        t3.left.right.right = new TreeNode(2);
        t3.right.left.left = new TreeNode(5);

        return new TreeNode[] {t1, t2, t3};
    }
}
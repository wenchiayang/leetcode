import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LC138
 */
public class LC138 {
    static class Node {
        int val;
        Node next;
        Node random;
    
        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }

        public void printNode() {
            Node temp = this;
            int index = 0;
            Map<Node, Integer> map = new HashMap<>();
            while (temp != null) {
                map.put(temp, index++);
                temp = temp.next;
            }

            Node curr = this;
            List<String> randoms = new ArrayList<>();

            while (curr != null) {
                if (curr.random != null) {
                    int position = map.get(curr.random);
                    randoms.add("[" + curr.val + " -> " + position + "]");
                } else {
                    randoms.add("[" + curr.val + " -> null]");
                }
                
                curr = curr.next;
                index++;
            }

            System.out.print(Arrays.toString(randoms.toArray()));
        }
    }

    /**
     * Solution
     * Mash Map(to avoid copy multiple times for the same node)
     * Idea: Build mapping between node in the original list
     * and the corresponding node in the new list
     * but changing the original list structure during the copy
     * (it will be changed back at the end)
     * https://www.youtube.com/watch?v=kGfsMookkzw
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * @param head
     * @return
     */
    public Node copyRandomList(Node head) {
        // check edge cases
        if (head == null) {
            return head;
        }

        // step 1: for each node in the original list
        // insert a copied node between the node and node.next
        // (1 -> 1' -> 2 -> 2' -> ... -> n -> n' -> null)
        // Step
        //      <----
        //         curr
        //          1 -> 2 -> 3 -> ... -> n -> null
        // copy(1')
        //   ----------->
        Node curr = head;
        while (curr != null) {
            // make a copy of curr node, insert it to 
            // the middle of curr and curr.next
            Node copy = new Node(curr.val);
            copy.next = curr.next;
            curr.next = copy;
            curr = curr.next.next;
        }

        // step 2: link the random pointer for the copied node
        //               curr copy
        // Origin List:   1 -> 1' -> 2 -> 2' -> ... -> n -> n' -> null
        // Origin Random: ---------->
        // Copy Random:        ---------->  
        curr = head;
        while (curr != null) {
            if (curr.random != null) {
                // copy node ----> copy node
                // (ex. 1')        (ex. 2')   
                curr.next.random = curr.random.next;
            }
            curr = curr.next.next;
        }

        // step 3: extract the copied node
        //       copied------->
        // Origin List:   1 -> 1' -> 2 -> 2' -> ... -> n -> n' -> null
        //               curr------->
        curr = head;
        Node dummy = new Node(-1);
        Node copied = dummy;

        while (curr != null) {
            // build copied
            copied.next = curr.next;
            // restore origin
            curr.next = curr.next.next;
            // move pointers
            copied = copied.next;
            curr = curr.next;
        }

        return dummy.next;
    }

    /**
     * Solution
     * Mash Map(to avoid copy multiple times for the same node)
     * Idea: Build mapping between node in the original list
     * and the corresponding node in the new list
     * https://www.youtube.com/watch?v=kGfsMookkzw
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * @param head
     * @return
     */
    public Node copyRandomList2(Node head) {
        // check edge cases
        if (head == null) {
            return head;
        }

        // create dummy node to help construct the deep copy(new list)
        Node dummy = new Node(-1);
        Node curr = dummy;

        // maintains the mapping between the node in the original list
        // and the corresponding node in the new list
        // key: origin node, value: new node 
        Map<Node, Node> map = new HashMap<>();

        // head: origin list
        // curr: new list
        while (head != null) {
            // copy the current node if necessary
            if (!map.containsKey(head)) {
                map.put(head, new Node(head.val));
            }

            // connect the copied code to the deep copy list
            curr.next = map.get(head);

            // copy the random node if necessary
            if (head.random != null) {
                if (!map.containsKey(head.random)) {
                    map.put(head.random, new Node(head.random.val));
                }

                // connect the copied node to the random pointer
                curr.next.random = map.get(head.random);
            }

            // move pointers
            head = head.next;
            curr = curr.next;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        System.out.println("138. Copy List with Random Pointer [medium]");
        LC138 solution = new LC138();
        Node[] inputs = {
            solution.getInput1(), solution.getInput2(), solution.getInput3(), null
        };

        for (int i = 0; i < inputs.length; i++) {
            System.out.println("Example " + (i + 1) + ":");
            Node input = inputs[i];
            System.out.print("Input: head = ");
            if (input != null) {
                input.printNode();
            } else {
                System.out.print("[]");
            }
            
            System.out.print("\nOutput:");
            Node output = solution.copyRandomList(input);
            if (output != null) {
                output.printNode();
            } else {
                System.out.print("[]");
            }
            System.out.println("\n");
        }
    }

    private Node getInput1() {
        Node head = new Node(7);
        head.next = new Node(13);
        head.next.next = new Node(11);
        head.next.next.next = new Node(10);
        head.next.next.next.next = new Node(1);
        head.random = null;
        head.next.random = head;
        head.next.next.random = head.next.next.next.next;
        head.next.next.next.random = head.next.next;
        head.next.next.next.next.random = head;
        return head;
    }

    private Node getInput2() {
        Node head = new Node(1);
        head.next = new Node(2);
        head.random = head.next;
        head.next.random = head.next;
        return head;
    }

    private Node getInput3() {
        Node head = new Node(3);
        head.next = new Node(3);
        head.next.next = new Node(3);
        head.random = null;
        head.next.random = head;
        head.next.next.random = null;
        return head;
    }
}
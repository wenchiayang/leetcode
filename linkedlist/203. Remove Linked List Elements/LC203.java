public class LC203 {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
        void printNodes() {
            ListNode curr = this;
    
            while (curr != null) {
                if (curr.next != null) {
                    System.out.print(curr.val + " -> ");
                } else {
                    System.out.print(curr.val);
                }
                
                curr = curr.next;
            }
        }
    }

    /**
     * Solution
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        
        ListNode prev = dummy;
        ListNode curr = head;

        while (curr != null) {
            if (curr.val == val) {
                prev.next = curr.next;
            } else { 
                // prev pointer will update only when curr.val != val
                prev = curr;
            }
            
            curr = curr.next;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        System.out.println("203. Remove Linked List Elements");
        ListNode head = new ListNode(6);
        head.next = new ListNode(6);
        head.next.next = new ListNode(6);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next.next = new ListNode(6);

        LC203 solution = new LC203();
        int val = 6;
        ListNode output = solution.removeElements(head, val);
        output.printNodes();
    }
}
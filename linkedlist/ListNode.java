public class ListNode {
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
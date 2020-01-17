/**
 * LC2
 */
public class LC2 {
    /**
     * Solution
     * Time Complexity: O(max(m, n)), where m, n are length of l1 and l2
     * Space Complexity: O(max(m, n)). The length of the new list is at most max(m,n) + 1.
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;
        ListNode p1 = l1, p2 = l2;
        int sum = 0;

        while (p1 != null || p2 != null) {
            if (p1 != null) {
                sum += p1.val;
                p1 = p1.next;
            }

            if (p2 != null) {
                sum += p2.val;
                p2 = p2.next;
            }

            curr.next = new ListNode(sum % 10);
            sum /= 10;
            curr = curr.next;
        }
        
        // with carry
        if (sum == 1) {
            curr.next = new ListNode(1);
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(7);

        LC2 solution = new LC2();
        ListNode output = solution.addTwoNumbers(l1, l2);
        
        System.out.println("2. Add Two Numbers [medium]");
        System.out.println("Input: (2 -> 4 -> 3) + (5 -> 6 -> 7)");
        System.out.print("Output: ");
        output.printNodes();
    }
}
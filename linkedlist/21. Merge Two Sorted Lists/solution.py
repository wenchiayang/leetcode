# Definition for singly-linked list.
class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None

class Solution:
    # Solution
    # Time Complexity: O(n + m), n = len(l1), m = len(l2)
    # Space Complexity: O(n + m)
    # The first call to mergeTwoLists does not return until 
    # the ends of both l1 and l2 have been reached, 
    # so n + m stack frames consume O(n + m) space.
    def mergeTwoLists_recursive(self, l1: ListNode, l2: ListNode) -> ListNode:
        if not l1:
            return l2
        elif not l2:
            return l1
        elif l1.val < l2.val:
            l1.next = self.mergeTwoLists_recursive(l1.next, l2)
            return l1
        else:
            l2.next = self.mergeTwoLists_recursive(l1, l2.next)
            return l2

    # Solution
    # Time Complexity: O(n + m), n = len(l1), m = len(l2)
    # Space Complexity: O(1)
    def mergeTwoLists_iterative(self, l1: ListNode, l2: ListNode) -> ListNode:
        dummy = ListNode(-1)
        head = dummy

        # compare l1 and l2, start from small value
        while l1 and l2:
            if l1.val < l2.val:
                dummy.next = l1
                l1 = l1.next
            else:
                dummy.next = l2
                l2 = l2.next
            dummy = dummy.next

        # concate remaining pointers
        if l1:
            dummy.next = l1
        if l2:
            dummy.next = l2

        return head.next

# Test
def print_list(tag, llist):
    if llist is None:
        print(tag + ' is an empty list.')
        return

    s = ''
    curr = llist
    while curr:
        s += str(curr.val) + ' '
        curr = curr.next

    print(tag + ': ' + s)

s = Solution()
llist1 = ListNode(1)
llist1.next = ListNode(2)
llist1.next.next = ListNode(4)
llist2 = ListNode(1)
llist2.next = ListNode(3)
llist2.next.next = ListNode(4)
print_list('llist1:', llist1)
print_list('llist2:', llist2)
# llist1 = s.mergeTwoLists_iterative(llist1, llist2)
llist1 = s.mergeTwoLists_recursive(llist1, llist2)
print_list('merged:', llist1)
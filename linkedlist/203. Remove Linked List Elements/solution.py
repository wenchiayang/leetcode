# Definition for singly-linked list.
class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None

class Solution:
    def removeElements(self, head: ListNode, val: int) -> ListNode:
        if head is None:
            return

        dummy = ListNode(-1)
        dummy.next = head
        curr = dummy
        while curr.next:
            if curr.next.val == val:
                curr.next = curr.next.next
            else:
                curr = curr.next
        return dummy.next

# Main
def print_list(llist):
    if llist is None:
        print('Empty list.')

    curr = llist
    while curr:
        print(curr.val)
        curr = curr.next

s = Solution()
llist = ListNode(1)
llist.next = ListNode(2)
llist = s.removeElements(llist, 1)
print_list(llist)


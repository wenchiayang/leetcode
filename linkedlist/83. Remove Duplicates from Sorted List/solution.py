# Definition for singly-linked list.
class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None

class Solution:
    def deleteDuplicates(self, head: ListNode) -> ListNode:
        # check if head is null
        if head is None:
            return
        
        seen = set()
        prev = None
        curr = head
        while curr: 
            if curr.val not in seen:
                # update seen and prev
                seen.add(curr.val)
                prev = curr
            else:
                # remove node
                prev.next = curr.next
                curr = None
            curr = prev.next
        return head

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
llist.next = ListNode(1)
llist.next.next = ListNode(2)
llist.next.next.next = ListNode(3)
llist.next.next.next.next = ListNode(3)
llist = s.deleteDuplicates(llist)
print_list(llist)
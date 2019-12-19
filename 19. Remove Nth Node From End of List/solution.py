# Definition for singly-linked list.
class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None

class Solution:
    # Solution: Two pass algorithm
    # Time Complexity: O(L)
    # Space Complexity: O(1)
    def removeNthFromEnd_two_pass(self, head: ListNode, n: int) -> ListNode:
        dummy = ListNode(-1)
        dummy.next = head
        length = 0
        first = head
        # calculate length of head
        while first:
            length += 1
            first = first.next
        # set number to remove from the begining
        # ex: if length = 5, n = 2, then remove no.3
        length -= n
        first = dummy
        while length > 0:
            length -= 1
            first = first.next
        first.next = first.next.next
        return dummy.next

    # Solution: One pass algorithm
    # Time Complexity: O(L)
    # Space Complexity: O(1)
    def removeNthFromEnd_one_pass(self, head: ListNode, n: int) -> ListNode:
        dummy = ListNode(-1)
        dummy.next = head
        first = second = dummy
        # Advances first pointer so that the gap between first and second is n nodes apart
        for _ in range(n + 1):
            first = first.next
        # Move first to the end, maintaining the gap
        while first:
            first = first.next
            second = second.next
        # delete target node
        second.next = second.next.next
        return dummy.next

    # Mine
    # Time Complexity: O(L)
    # Space Complexity: O(1)
    def removeNthFromEnd(self, head: ListNode, n: int) -> ListNode:
        length = self.len_list_node(head)
        curr = head
        prev = None
        while curr:
            if length < n + 1:
                if prev: # delete middle
                    prev.next = curr.next
                    break
                else: # delete head
                    return head.next
            length -= 1
            prev = curr
            curr = curr.next
        return head

    def len_list_node(self, head):
        count = 0
        curr = head
        while curr:
            count += 1
            curr = curr.next
        return count

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
llist.next.next = ListNode(3)
llist.next.next.next = ListNode(4)
llist.next.next.next.next = ListNode(5)
# llist = s.removeNthFromEnd(llist, 2)
# llist = s.removeNthFromEnd_two_pass(llist, 2)
llist = s.removeNthFromEnd_one_pass(llist, 2)
print_list(llist)
# Definition for singly-linked list.
class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None

class Solution:
    # Recursive Solution
    # Time Complexity: O(n)
    # Space Complexity: O(n)
    def swapPairs_recursive(self, head: ListNode) -> ListNode:
        # If the list has no node or has only one node left.
        if not head or not head.next:
            return head

        # Nodes to be swapped
        first = head
        second = head.next

        # Swapping
        first.next  = self.swapPairs_recursive(second.next)
        second.next = first

        # Now the head is the second node
        return second

    # Iterative Solution
    # Time Complexity: O(n)
    # Space Complexity: O(1)
    def swapPairs_iterative(self, head: ListNode) -> ListNode:
        # Dummy node acts as the prevNode for the head node
        # of the list and hence stores pointer to the head node.
        dummy = ListNode(-1)
        dummy.next = head

        prev = dummy
        while head and head.next:
            # Nodes to be swapped
            first = head
            second = head.next

            # swapping
            prev.next = second
            first.next = second.next
            second.next = first

            # Reinitializing the head and previous node for next step
            prev = first
            head = first.next
            # print('head:')
            # self.print_list(head)
            # print()
            # print('dummy:')
            # self.print_list(dummy)
            # print()

        # Return the new head node
        return dummy.next

    def print_list(self, llist):
        if llist is None:
            print('Empty list.')

        curr = llist
        while curr:
            print(curr.val)
            curr = curr.next

# Test
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
print('Before')
print_list(llist)
print('\nAfter')
# llist = s.swapPairs_recursive(llist)
llist = s.swapPairs_iterative(llist)
print_list(llist)
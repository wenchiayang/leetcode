# Definition for singly-linked list.
class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None

class Solution:
    # Mine
    # 3 pointers(curr, prev, next), iteratively
    # Time Complexity: O(n)
    # Space Complexity: O(1)
    def reverseList_iterative(self, head: ListNode) -> ListNode:
        prev = None
        curr = head
        while curr:
            next = curr.next
            curr.next = prev
            prev = curr
            curr = next
        head = prev
        return head

    # Mine
    # recursively
    # Time Complexity: O(n)
    # Space Complexity: O(n). 
    # The extra space comes from implicit stack space due to recursion. 
    # The recursion could go up to n levels deep.
    def reverseList_recursive(self, head: ListNode) -> ListNode:
        def reverse_recursive(curr, prev):
            # base case
            if not curr:
                print('curr is None')
                return prev
            
            print('curr is not None')
            next = curr.next
            curr.next = prev
            prev = curr
            curr = next

            print('curr:')
            print_list(curr)
            print('prev:')
            print_list(prev)
            print()

            return reverse_recursive(curr, prev)
        head = reverse_recursive(curr=head, prev=None)
        return head

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
# llist = s.reverseList_iterative(llist)
llist = s.reverseList_recursive(llist)
print_list(llist)
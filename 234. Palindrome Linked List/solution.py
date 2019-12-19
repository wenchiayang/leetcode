# Definition for singly-linked list.
class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None

class Solution:
    # Mine
    # Two pointers
    # Time Complexity: O(n)
    # Space Complexity: O(1)
    def isPalindrome(self, head: ListNode) -> bool:
        def reverse(head):
            prev = None
            curr = head
            while curr is not None:
                next = curr.next
                curr.next = prev
                prev = curr
                curr = next
            return prev
        
        # make slow and fast pointers
        slow = fast = head
        
        # Advanced fast pointer(fast = 2 times slow)
        while fast and fast.next is not None:
            fast = fast.next.next
            slow = slow.next
            
        slow = reverse(slow)
        fast = head
        
        # compare slow and fast pointers
        while slow is not None:
            if slow.val != fast.val:
                return False
            slow = slow.next
            fast = fast.next
        
        return True

    # Mine
    # stack
    # Time Complexity: O(n)
    # Space Complexity: O(n)
    def isPalindrome_stack(self, head: ListNode) -> bool:
        s = []
        curr = head
        while curr:
            s.append(curr.val)
            curr = curr.next
        curr = head
        while curr:
            if curr.val != s.pop():
                return False
            curr = curr.next
        return True


# Test
s = Solution()
llist = ListNode(-129)
llist.next = ListNode(-129)
# llist.next.next = ListNode(3)
# llist.next.next.next = ListNode(4)
# llist.next.next.next.next = ListNode(5)
# print(s.isPalindrome(llist))
# print(s.isPalindrome_stack(llist))
print(s.isPalindrome_string(llist))

# Definition for singly-linked list.
class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None

class Solution:
    # Solution
    # Time Complexity: O(n)
    # Space Complexity: O(n)
    def hasCycle_hash_map(self, head: ListNode) -> bool:
        seen = set()
        curr = head
        while curr:
            if curr in seen:
                return True
            else:
                seen.add(curr)
            curr = curr.next
        return False
        
    def hasCycle_two_pointers2(self, head: ListNode) -> bool:
        # check base case
        if not head or not head.next:
            return False
        
        # two pointers
        slow = head
        fast = head
        
        while fast and fast.next:
            slow = slow.next
            fast = fast.next.next
            
            if slow == fast:
                return True
            
        return False
    
    # Solution
    # Time Complexity: O(n)
    # Space Complexity: O(1)
    def hasCycle_two_pointers(self, head: ListNode) -> bool:
        # check base case
        if not head:
            return False
        
        # two pointers
        slow = head
        fast = head.next
        while slow is not fast:
            if not fast or not fast.next:
                return False
            slow = slow.next
            fast = fast.next.next
            
        return True
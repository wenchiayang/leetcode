# Definition for singly-linked list.
class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None

class Solution:
    # Solutoin
    # Time Complexity: O(n)
    # Space Complexity: O(n)
    def detectCycle_hash_table(self, head: ListNode) -> ListNode:
        seen = set()
        curr = head
        while curr:
            if curr in seen:
                return curr
            else:
                seen.add(curr)
            curr = curr.next
        return None

    # Solutoin
    # Time Complexity: O(n)
    # Space Complexity: O(1)
    def detectCycle_two_pointers(self, head: ListNode) -> ListNode:
        # check base case
        # check base case
        if not head:
            return None

        # If there is a cycle, the fast/slow pointers will intersect at some
        # node. Otherwise, there is no cycle, so we cannot find an entrance to
        # a cycle.
        intersect = self.getIntersect(head)
        if not intersect:
            return None

        # To find the entrance to the cycle, we have two pointers traverse at
        # the same speed -- one from the front of the list, and the other from
        # the point of intersection.
        slow = head
        fast = intersect
        while slow != fast:
            slow = slow.next
            fast = fast.next

        return slow

    def getIntersect(self, head):
        slow = head
        fast = head

        # A fast pointer will either loop around a cycle and meet the slow
        # pointer or reach the `null` at the end of a non-cyclic list.
        while fast and fast.next:
            slow = slow.next
            fast = fast.next.next
            if slow == fast:
                return slow
        return None
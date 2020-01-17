# Definition for singly-linked list.
class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None

class Solution:
    # Solution
    # Time Complexity: O(n)
    # Space Complexity: O(1)
    def getIntersectionNode(self, headA: ListNode, headB: ListNode) -> ListNode:
        lengthA = self.get_length(headA)
        lengthB = self.get_length(headB)
        
        # move pointer to make headA and headB has same length
        while lengthA > lengthB:
            headA = headA.next
            lengthA -= 1

        while lengthB > lengthA:
            headB = headB.next
            lengthB -= 1

        # find pointer of the intersect node
        while headA is not headB:
            headA = headA.next
            headB = headB.next

        return headA

    def get_length(self, head):
            length = 0
            curr = head
            while curr:
                length += 1
                curr = curr.next
            return length
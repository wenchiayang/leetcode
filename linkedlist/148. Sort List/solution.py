# Definition for singly-linked list.
class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None

class Solution:
    # Solution: Bottom-up
    # Time Complexity: O(nlogn)
    # Space Complexity: O(1)
    def sortList_bottom_up(self, head: ListNode) -> ListNode:
        pass

    def merge_top_down(self, left: ListNode, right: ListNode):
        pass

    # Solution: Top-Down
    # Time Complexity: O(nlogn)
    # Space Complexity: O(logn)
    def sortList_top_down(self, head: ListNode) -> ListNode:
        # Check edge cases
        if not head or not head.next:
            return head

        # Split head into two lists
        temp = head
        slow = head
        fast = head

        while fast and fast.next:
            temp = slow
            slow = slow.next
            fast = fast.next.next

        temp.next = None

        # recursively split list and merge
        left = self.sortList_top_down(head)
        right = self.sortList_top_down(slow)

        return self.merge_top_down(left, right)

    def merge_top_down(self, left: ListNode, right: ListNode) -> ListNode:
        sorted_temp = ListNode(-1)
        curr = sorted_temp

        # compare left and right, start from small value
        while left and right:
            if left.val < right.val:
                curr.next = left
                left = left.next
            else:
                curr.next = right
                right = right.next
            curr = curr.next

         # concate remaining pointers
        if left:
            curr.next = left
        if right:
            curr.next = right

        return sorted_temp.next





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

def get_test_node_1() -> ListNode:
    llist = ListNode(4)
    llist.next = ListNode(1)
    llist.next.next = ListNode(2)
    llist.next.next.next = ListNode(3)
    return llist

def get_test_node_2() -> ListNode:
    llist = ListNode(-1)
    llist.next = ListNode(5)
    llist.next.next = ListNode(3)
    llist.next.next.next = ListNode(4)
    llist.next.next.next.next = ListNode(0)
    return llist

# Main
s = Solution()
print('case 1')
llist = get_test_node_1()
print_list('before', llist)
llist = s.sortList_top_down(llist)
print_list('after ', llist)
print('case 2')
llist = get_test_node_2()
print_list('before', llist)
llist = s.sortList_top_down(llist)
print_list('after ', llist)
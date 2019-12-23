# Definition for singly-linked list.
class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None

class Solution:
    def reorderList(self, head: ListNode) -> None:
        """
        Do not return anything, modify head in-place instead.
        """
        # check invalid cases
        if not head or not head.next:
            return

        # split head into 2 halves
        l1 = head # head of first half
        slow = head # head of second half
        prev = None # tail of first half
        fast = head # tail of second half

        while fast and fast.next:
            prev = slow
            slow = slow.next
            fast = fast.next.next

        prev.next = None

        # self.print_list('l1', l1)
        # self.print_list('slow', slow)
        # self.print_list('prev', prev)
        # self.print_list('fast', fast)

        # reverse 2nd havles
        l2 = self.reverse(slow)
        # self.print_list('l2', l2)
        # merge 1st and 2nd halves
        self.merge(l1, l2)
        self.print_list('result', l1)

    def reverse(self, head):
        prev = None
        curr = head

        while curr:
            next = curr.next
            curr.next = prev
            prev = curr
            curr = next

        return prev

    def merge(self, l1, l2):
        while l1:
            l1_next = l1.next
            l2_next = l2.next

            # merge
            l1.next = l2

            if not l1_next:
                break

            l2.next = l1_next

            # move to next
            l1 = l1_next
            l2 = l2_next

    def print_list(self, tag, llist):
        if llist is None:
            print(tag + ' is an empty list.')
            return

        s = ''
        curr = llist
        while curr:
            s += str(curr.val) + ' '
            curr = curr.next

        print(tag + ': ' + s)

# Test
def print_list(llist):
    if llist is None:
        print('Empty list.')

    curr = llist
    while curr:
        print(curr.val)
        curr = curr.next

s = Solution()
# llist = ListNode(1)
# llist.next = ListNode(2)
# llist.next.next = ListNode(3)
# llist.next.next.next = ListNode(4)
llist = ListNode(1)
llist.next = ListNode(2)
llist.next.next = ListNode(3)
llist.next.next.next = ListNode(4)
llist.next.next.next.next = ListNode(5)
print('Before')
print_list(llist)
print('\nAfter')
llist = s.reorderList(llist)
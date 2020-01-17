# Definition for singly-linked list.
class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None

class Solution:
    def deleteDuplicates(self, head: ListNode) -> ListNode:
#         # check null list
#         if not head:
#             return None
        
#         # check if only 1 node in the list
#         if not head.next:
#             return head
        
        dummy = ListNode(-1)
        dummy.next = head
        prev = dummy
        curr = head

        while curr:
            # jump duplicate node            
            while curr.next and curr.val == curr.next.val:
                curr = curr.next
            
            if prev.next == curr:
                prev = prev.next
            else:
                prev.next = curr.next
                
            curr = curr.next
                
        return dummy.next

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

def test_node1() -> ListNode:
    llist = ListNode(1)
    llist.next = ListNode(2)
    llist.next.next = ListNode(3)
    llist.next.next.next = ListNode(3)
    llist.next.next.next.next = ListNode(4)
    llist.next.next.next.next.next = ListNode(4)
    llist.next.next.next.next.next.next = ListNode(5)
    return llist

def test_node2() -> ListNode:
    llist = ListNode(1)
    llist.next = ListNode(1)
    llist.next.next = ListNode(1)
    llist.next.next.next = ListNode(2)
    llist.next.next.next.next = ListNode(3)
    return llist

# Main
s = Solution()
llist = test_node1()
print_list('case1(before)', llist)
llist = s.deleteDuplicates(llist)
print_list('case1(after)', llist)

llist = test_node2()
print_list('case2(before)', llist)
llist = s.deleteDuplicates(llist)
print_list('case2(after)', llist)
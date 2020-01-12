# Definition for singly-linked list.
class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None

class Solution:
    def sortList(self, head: ListNode) -> ListNode:
        if not head or not head.next:
            return head
        slow = fast = ListNode(0)
        slow.next = head
        # get to the middle node of the list
        while fast and fast.next:
            slow = slow.next
            fast = fast.next.next
        head2 = slow.next
        slow.next = None
        newHead1 = self.sortList(head)
        newHead2 = self.sortList(head2)
        return self.merge(newHead1, newHead2)
    
    def merge(self, head1: ListNode, head2: ListNode) -> ListNode:
        if not head1:
            return head2
        elif not head2:
            return head1
        cur = dummy = ListNode(0)
        cur1, cur2 = head1, head2
        while cur1 and cur2:
            if cur1.val < cur2.val:
                cur.next = cur1
                cur1 = cur1.next
            else:
                cur.next = cur2
                cur2 = cur2.next
            cur = cur.next
        cur.next = cur2 if not cur1 else cur1
        return dummy.next
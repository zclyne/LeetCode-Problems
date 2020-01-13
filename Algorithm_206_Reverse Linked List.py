# Definition for singly-linked list.
class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None

class Solution:
    def reverseList(self, head: ListNode) -> ListNode:
        if not head or not head.next:
            return head
        cur, next = head, head.next
        head.next = None
        while next:
            newNext = next.next
            next.next = cur
            cur = next
            next = newNext
        return cur
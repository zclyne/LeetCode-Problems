# Definition for singly-linked list.
class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None

class Solution:
    def removeNthFromEnd(self, head: ListNode, n: int) -> ListNode:
        first = second = dummy = ListNode(0)
        dummy.next = head
        for _ in range(n + 1):
            first = first.next
        while first:
            first, second = first.next, second.next
        second.next = second.next.next
        return dummy.next
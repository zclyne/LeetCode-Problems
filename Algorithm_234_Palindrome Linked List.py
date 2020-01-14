# Definition for singly-linked list.
class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None

class Solution:
    def isPalindrome(self, head: ListNode) -> bool:
        if not head or not head.next:
            return True
        slow = fast = head
        mid = ListNode(0)
        while fast and fast.next:
            fast = fast.next.next
            nextSlow = slow.next
            slow.next = mid
            mid = slow
            slow = nextSlow
        cur1 = mid # head of the left list
        cur2 = slow if not fast else slow.next # head of the right list
        # because the tail of the left list is a dummy node ListNode(0)
        # we only need to check whether cur2 is None
        while cur2:
            if cur1.val != cur2.val:
                return False
            cur1, cur2 = cur1.next, cur2.next
        return True
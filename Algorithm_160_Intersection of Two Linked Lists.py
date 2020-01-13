# Definition for singly-linked list.
class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None

class Solution:
    def getIntersectionNode(self, headA: ListNode, headB: ListNode) -> ListNode:
        curA, curB = headA, headB
        while curA != curB:
            # notice: must be "not curA", not "not curA.next"
            # otherwise if there is not intersection, the loop won't stop
            curA = headB if not curA else curA.next
            curB = headA if not curB else curB.next
        return curA
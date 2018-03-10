# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def addTwoNumbers(self, l1, l2):
        """
        :type l1: ListNode
        :type l2: ListNode
        :rtype: ListNode
        """
        carry=0
        head=ListNode(-1)
        tmp=head
        while l1 or l2 or carry:
            if l1:
                n1=l1.val
                l1=l1.next
            else:
                n1=0
            if l2:
                n2=l2.val
                l2=l2.next
            else:
                n2=0
            thisBit=n1+n2+carry
            carry=thisBit//10
            tmp.next=ListNode(thisBit%10)
            tmp=tmp.next
        return head.next
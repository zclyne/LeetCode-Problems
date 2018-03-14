# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def removeNthFromEnd(self, head, n):
        num,cur=0,1
        tmp=head
        while tmp!=None:
            num+=1
            tmp=tmp.next
        if n>num: return head
        if n==num: return head.next
        tmp=head
        while cur<num-n:
            cur+=1
            tmp=tmp.next
        tmp.next=tmp.next.next
        return head
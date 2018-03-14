# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def swapPairs(self, head):
        """
        :type head: ListNode
        :rtype: ListNode
        """
        if head==None or head.next==None: return head
        newHead=head.next
        node1,node2=head,head.next
        while node1!=None and node2!=None:
            tmp=node1
            node1=node2
            tmp.next=node2.next
            node1.next=tmp
            node2=tmp
            node1=node1.next.next
            node2=node2.next
            if node2!=None: node2=node2.next
        return newHead
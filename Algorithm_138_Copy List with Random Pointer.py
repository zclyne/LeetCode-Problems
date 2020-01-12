# Definition for a Node.
class Node:
    def __init__(self, x: int, next: 'Node' = None, random: 'Node' = None):
        self.val = int(x)
        self.next = next
        self.random = random

class Solution:
    def copyRandomList(self, head: 'Node') -> 'Node':
        if not head:
            return None
        # first round: copy each node without copying random
        cur = head
        while cur:
            nextCur = cur.next
            cur.next = Node(cur.val)
            cur.next.next = nextCur
            cur = nextCur
        # second round: get random for newly created nodes
        cur = head
        while cur:
            nextCur = cur.next.next
            cur.next.random = None if not cur.random else cur.random.next
            cur = nextCur
        # third round: extract the new list and recover the old list
        newCur = dummy = Node(0)
        cur = head
        while cur:
            nextCur = cur.next.next
            newCur.next = cur.next
            cur.next = nextCur
            newCur = newCur.next
            cur = nextCur
        return dummy.next
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

// 思路：由于题干保证给定的node不是链表的最后一个节点，因此直接把该节点的val置为其下一个节点的val并修改next使其指向再下一个节点

class Solution {
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
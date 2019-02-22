// 思路：两个指针同时从两链表的起始节点开始走，当某一指针到达null时，将其置为另一个链表的头节点，并继续向前走
// 若两链表有交叉，则当a等于b时，a恰好为交点，返回a
// 若无交叉，a和b将同时到达链表尾部的null，返回null
// 证明：假设两链表有交点，交点之后的长度为l3，之前的长度分别为l1和l2，不妨设l1 < l2
// 指针a将先于b到达null，此时a将指向headB；随后，指针b也到达null，并重新指向headA
// 两指针所走的距离始终相同，因此相遇时，他们都走了l1 + l2 + l3的距离，因此交点恰好为交点

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}

class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode a = headA, b = headB;
        while (a != b) {
            a = a == null ? headB : a.next;
            b = b == null ? headA : b.next;
        }
        return a;
    }
}
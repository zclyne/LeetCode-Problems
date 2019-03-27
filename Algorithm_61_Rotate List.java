// 思路：先遍历一遍链表，遍历的同时用变量len记录链表的长度，然后把链表的最后一个节点tail的next设为head，构成环
// 把k对len取模，防止多次rotate之后又回到最初的链表
// 若取模后的k是0，则不需要rotate，直接把链表的环断开后返回原本的head
// 若取模后的k不是0，则把tail和head同时向后走len - k次，到达要返回的新的head
// 然后把tail.next置为null，断开链表，最后返回head

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        int len = 1;
        ListNode tail = head;
        while (tail.next != null) {
            len++;
            tail = tail.next;
        }
        tail.next = head;
        k %= len;
        if (k != 0) {
            for (int i = 0; i < len - k; i++) {
                tail = tail.next;
                head = head.next;
            }
        }
        tail.next = null;
        return head;
    }
}
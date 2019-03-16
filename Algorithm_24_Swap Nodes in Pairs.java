// 思路：画图。cur和n分别为要交换的两个结点，prev是上一个pair交换后的第二个结点
// 把一个pair中的2个结点交换后，prev.next = n，n.next = cur, cur.next = newCur

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

class Solution {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = new ListNode(0), prev = newHead, cur = head, n = head.next;
        newHead.next = head;
        while (n != null) {
            ListNode newCur = n.next;
            prev.next = n;
            n.next = cur;
            cur.next = newCur;
            prev = cur;
            cur = newCur;
            n = newCur == null ? null : newCur.next;
        }
        return newHead.next;
    }
}
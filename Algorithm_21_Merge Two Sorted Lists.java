// 思路：用2个指针遍历2个链表，根据两指针对应值的大小，取较小的作为新的节点，并把指针向后挪一位

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode cur = new ListNode(0);
        ListNode start = cur;
        while (l1 != null && l2 != null) {
            int val1 = l1.val, val2 = l2.val;
            if (val1 < val2) {
                cur.next = new ListNode(val1);
                l1 = l1.next;
            } else {
                cur.next = new ListNode(val2);
                l2 = l2.next;
            }
            cur = cur.next;
        }
        if (l1 != null) {
            cur.next = l1;
        } else {
            cur.next = l2;
        }
        return start.next;
    }
}
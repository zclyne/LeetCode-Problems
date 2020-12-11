/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

// 思路：与数组的插入排序不同，因为此处是单向链表，无法从后向前遍历
// 所以使用从前向后的顺序来寻找要插入的位置

class Solution {
    public ListNode insertionSortList(ListNode head) {
        ListNode dummyHead = new ListNode(0);
        ListNode cur = head;
        while (cur != null) {
            ListNode pre = dummyHead;
            while (pre.next != null && pre.next.val < cur.val) {
                pre = pre.next;
            }
            ListNode preNext = pre.next;
            ListNode curNext = cur.next;
            pre.next = cur;
            cur.next = preNext;
            cur = curNext;
        }
        return dummyHead.next;
    }
}
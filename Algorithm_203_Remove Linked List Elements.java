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

// 方法：构建一个新的头结点dummyHead，直接遍历链表，pre记录cur的上一个节点

class Solution {
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummyHead = new ListNode(0, head);
        ListNode pre = dummyHead, cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            if (cur.val == val) {
                pre.next = next;
                cur = next;
            } else {
                pre = cur;
                cur = next;
            }
        }
        return dummyHead.next;
    }
}
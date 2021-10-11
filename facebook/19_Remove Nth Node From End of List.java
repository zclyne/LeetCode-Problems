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

// two pointers, use dummyHead to handle the edge case that the head node is the one to be deleted

class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode cur = dummyHead, pre = dummyHead;
        while (n > 0) {
            cur = cur.next;
            n--;
        }
        while (cur.next != null) {
            cur = cur.next;
            pre = pre.next;
        }
        // remove the next node of pre
        pre.next = pre.next.next;
        return dummyHead.next;
    }
}
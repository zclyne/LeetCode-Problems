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

// 思路：先找到链表的中间节点，然后将中间节点的右半部分反转，最后交替连接两部分中的节点

class Solution {
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }
        // look for the milddle of the list
        ListNode p1 = head, p2 = head;
        while (p2.next != null && p2.next.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;
        }
        ListNode pre = null, current = p1.next, firstEnd = p1;
        // cut the list after firstEnd because firstEnd must be the end
        // of the reordered list
        firstEnd.next = null;
        // reverse the second half
        while (current != null) {
            ListNode nextCur = current.next;
            current.next = pre;
            pre = current;
            current = nextCur;
        }
        // merge the first half and the second half
        p1 = head; p2 = pre;
        // only need to check whether p2 is null, because the length of the first half
        // is always equal to or greater than the second half, therefore, p2 should encounter null
        // earlier than p1
        while (p2 != null) {
            ListNode p1Next = p1.next, p2Next = p2.next;
            p1.next = p2;
            p2.next = p1Next;
            p1 = p1Next;
            p2 = p2Next;
        }
    }
}
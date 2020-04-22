/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

 // 思路：Straight-forward

class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(0);
        ListNode lastDistinctNode = dummy, cur = head, newCur = dummy;
        while (cur != null) {
            boolean duplicate = false;
            while (cur.next != null && cur.next.val == cur.val) {
                duplicate = true;
                cur = cur.next;
            }
            ListNode nextNode = cur.next;
            if (!duplicate) {
                newCur.next = cur;
                newCur = newCur.next;
                newCur.next = null;
            }
            cur = nextNode;   
        }
        return dummy.next;
    }
}
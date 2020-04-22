/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

// 思路：先找到要反转的链表的startNode和endNode，记录下startNode之前的node以及endNode之后的node
// 再用链表反转的方法反转startNode和endNode之间的链表，最后把反转完成的链表接回去

class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = dummy;
        int count = 0;
        // look for the start node
        while (count < m - 1) {
            cur = cur.next;
            count++;
        }
        ListNode preStartNode = cur, startNode = cur.next;
        // look for the end node
        while (count < n) {
            cur = cur.next;
            count++;
        }
        ListNode endNode = cur, endNodeNext = endNode.next;
        // reverse the list between startNode and endNode
        reverse(startNode, endNode);
        // connect the reversed list back, because the list has been reversed, startNode has become the new endNode
        // and endNode has become the new startNode
        startNode.next = endNodeNext;
        preStartNode.next = endNode;
        return dummy.next;
    }

    // reverse the linkedlist which starts with startNode and ends with endNode
    // and returns the new startNode and endNode in an array
    private void reverse(ListNode startNode, ListNode endNode) {
        endNode.next = null;
        ListNode pre = startNode, cur = startNode.next, newEnd = pre;
        while (cur != null) {
            ListNode nextNode = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nextNode;
        }
    }
}
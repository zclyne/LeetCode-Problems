/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

// iterative solution
// 直接做，画个图更容易理解
// preEnd记录上一组（已经完成了reverse）的最后一个节点，cur为当前节点，pre为cur的前一个节点
// nextNode为cur的下一个节点，newHead为要返回的链表的头节点

class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null || k == 1) {
            return head;
        }
        ListNode curHead = head, cur = head, pre = null, preEnd = null, newHead = null;
        int count = 0;
        while (cur != null) {
            ListNode nextNode = cur.next;
            cur.next = pre;
            count++;
            if (count == k) { // reach the end of a group
                curHead.next = nextNode;
                if (preEnd == null) { // this is the first group
                    newHead = cur;
                } else { // connect the end of the last group to the head of this group
                    preEnd.next = cur;
                }
                // as the current k group has been reversed, curHead is actually the end of the group
                preEnd = curHead;
                pre = curHead;
                curHead = nextNode;
                count = 0; // reset count
            } else {
                cur.next = pre;
                pre = cur;
            }
            cur = nextNode;
        }
        if (count != 0) { // the last group has less than k nodes, reverse again
            cur = pre;
            pre = null;
            while (cur != curHead) {
                ListNode nextNode = cur.next;
                cur.next = pre;
                pre = cur;
                cur = nextNode;
            }
            cur.next = pre;
        }
        return newHead;
    }
}

// recursive solution

class RecursiveSolution {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode curr = head;
        int count = 0;
        while (curr != null && count != k) { // find the k+1 node
            curr = curr.next;
            count++;
        }
        if (count == k) { // if k+1 node is found
            curr = reverseKGroup(curr, k); // reverse list with k+1 node as head
            // head - head-pointer to direct part, 
            // curr - head-pointer to reversed part;
            while (count-- > 0) { // reverse current k-group: 
                ListNode tmp = head.next; // tmp - next head in direct part
                head.next = curr; // preappending "direct" head to the reversed list 
                curr = head; // move head of reversed part to a new node
                head = tmp; // move "direct" head to the next node in direct part
            }
            head = curr;
        }
        return head;
    }
}
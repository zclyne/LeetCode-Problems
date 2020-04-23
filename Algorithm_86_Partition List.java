/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

// 思路：用两个ListNode lessHead和biggerHead分别代表比x的小的链表的头节点和比x大的链表的头节点
// lessCur和biggerCur表示两链表的当前节点，cur表示遍历原链表时的当前节点
// 遍历原链表，若当前节点值小于x，则把cur加入到lessCur.next，并把lessCur向后移动一位
// 否则，把cur加入到biggerCur.next，并把biggerCur向后移动一位
// 最后，把biggerHead.next，也就是比x大的链表的头节点接到lessCur.next，也就是比x小的链表的尾部节点之后
// 并令biggerCur.next = null，也就是把比x大的链表的最后一位的next置为null
// 最终lessHead.next就是新链表的头节点

class Solution {
    public ListNode partition(ListNode head, int x) {
        ListNode cur = head, lessHead = new ListNode(0), biggerHead = new ListNode(0);
        ListNode lessCur = lessHead, biggerCur = biggerHead;
        while (cur != null) {
            if (cur.val < x) {
                lessCur.next = cur;
                lessCur = lessCur.next;
            } else {
                biggerCur.next = cur;
                biggerCur = biggerCur.next;
            }
            cur = cur.next;
        }
        lessCur.next = biggerHead.next;
        biggerCur.next = null;
        return lessHead.next;
    }
}
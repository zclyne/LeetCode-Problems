// 思路：快慢指针。两指针之间距离相差为n，且保持同样速度移动
// 当指针fast移动到结尾时，指针slow恰好为要删除的节点的前一个节点
// 因此执行slow.next = slow.next.next就可以删除目标节点
// 注意初始时设置slow.next = head是为了考虑[1, 2] n = 2这种情况
// 此时，要删除的节点恰好是head

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode slow = new ListNode(-1), newHead = slow, fast = head;
        slow.next = head;
        for (int i = 1; i < n; i++) {
            fast = fast.next;
        }
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return newHead.next;
    }
}
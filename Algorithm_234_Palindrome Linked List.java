// 思路：用快慢指针找到链表的中点，并在slow遍历的过程中对链表的前一半做反向。当fast到达链表末尾时，slow恰好处于链表的正中间
// 从slow开始向左右两边遍历，并比较当前节点的val是否相等。若链表是回文的，则反向后的前一半与未反向的后一半的每一个节点上的值都相等，直到遇到null
// 根据fast为null还是fast.next为null来判断链表总节点数是奇数还是偶数，从而决定后半部分的起点是当前slow还是slow.next
// 注意slow.next仍然是正向的，因此slow.next是slow在原链表中的后一个节点，而不是前一个节点

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

class Solution {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode prev = null, fast = head, slow = head;
        while (fast != null && fast.next != null) { // traverse the linkedlist and reverse the first half at the same time
            fast = fast.next.next;
            ListNode slowNext = slow.next;
            slow.next = prev;
            prev = slow;
            slow = slowNext;
        }
        ListNode firstHalfStart = prev;
        ListNode secondHalfStart = fast == null ? slow : slow.next; // pay attention here
        while (firstHalfStart != null && secondHalfStart != null && firstHalfStart.val == secondHalfStart.val) {
            firstHalfStart = firstHalfStart.next;
            secondHalfStart = secondHalfStart.next;
        }
        return firstHalfStart == null;
    }
}
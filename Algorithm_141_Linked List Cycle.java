// 思路：快慢指针。首先判断head是否为null，若为null则一定无环，返回false
// fast每次走2步，slow每次走一步，则fast相对于slow的速度为1
// 若原链表有环，则slow和fast最终一定会相遇，此时退出循环并返回true
// 若fast或fast.next为null，则一定无环，退出循环并返回false

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}

class Solution {
    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }
}
// 思路：使用快慢指针找到链表的中间位置，并将前一半的最后一个节点的next置为null，从而从中间切断链表
// 并对左右两半部分递归调用sortList，最后使用归并方法合并成一个链表

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

class Solution {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode prev = null, slow = head, fast = head;
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        prev.next = null; // cut the list
        ListNode n1 = sortList(head);
        ListNode n2 = sortList(slow);
        return merge(n1, n2);
    }

    public ListNode merge(ListNode n1, ListNode n2) {
        if (n1 == null) {
            return n2;
        } else if (n2 == null) {
            return n1;
        }
        ListNode start = new ListNode(0), cur = start;
        while (n1 != null && n2 != null) {
            if (n1.val < n2.val) {
                cur.next = n1;
                n1 = n1.next;
            } else {
                cur.next = n2;
                n2 = n2.next;
            }
            cur = cur.next;
        }
        if (n1 == null) {
            cur.next = n2;
        } else {
            cur.next = n1;
        }
        return start.next;
    }
}
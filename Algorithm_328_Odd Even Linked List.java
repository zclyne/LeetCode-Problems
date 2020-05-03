class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

// 思路：把奇数号节点和偶数号节点分别先用两个链表暂存起来，奇数号节点链表的当前节点为oddCur
// 偶数号节点链表的当前节点为evenCur。变量count记录当前是奇数还是偶数
// 根据count的值把cur分别添加到oddCur.next或evenCur.next，遍历完成后，把偶数节点链表
// 添加到奇数节点链表的尾部，再返回奇数节点链表的头节点

class Solution {
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode oddDummy = new ListNode(0), evenDummy = new ListNode(0);
        ListNode cur = head, oddCur = oddDummy, evenCur = evenDummy;
        int count = 1;
        while (cur != null) {
            ListNode curNext = cur.next;
            if (count % 2 == 1) {
                oddCur.next = cur;
                oddCur = oddCur.next;
            } else {
                evenCur.next = cur;
                evenCur = evenCur.next;
            }
            cur.next = null;
            cur = curNext;
            count++;
        }
        oddCur.next = evenDummy.next;
        return oddDummy.next;
    }
}
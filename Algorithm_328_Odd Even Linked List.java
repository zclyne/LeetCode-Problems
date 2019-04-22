class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

// My Solution
// 思路：先遍历一遍到达链表的末尾节点，记为tail
// 再遍历一遍，第二次遍历时把所有偶数位置上的节点都添加到tail的后面
// 循环终止条件是cur == null || cur.next == null，分别对应总节点数为奇数和偶数的情况
// 注意链表中节点数为0个、1个或2个时都可以直接返回head

class Solution {
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        ListNode tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        ListNode cur = head, newTail = tail;
        while (cur != tail && cur.next != tail) {
            newTail.next = cur.next;
            cur.next = cur.next.next;
            newTail = newTail.next;
            newTail.next = null;
            cur = cur.next;
        }
        if (cur.next == tail) { // total num of nodes is even
            cur.next = tail.next;
            newTail.next = tail;
            tail.next = null;
        }
        return head;
    }
}

// Better Solution
// 思路：用odd和even分别记录当前遍历到的奇数节点和偶数节点，遍历过程中修改各自的next指向
// 遍历完成后，把even部分的头节点（即evenHead）连接到odd.next上

class BetterSolution {
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        
        ListNode odd = head, even = head.next, evenHead = even;
        while (even != null && even.next != null) {
            odd.next = odd.next.next;
            odd = odd.next;
            even.next = even.next.next;
            even = even.next;
        }
        odd.next = evenHead;

        return head;
    }
}
class Node {
    public int val;
    public Node next;
    public Node random;

    public Node() {}

    public Node(int _val,Node _next,Node _random) {
        val = _val;
        next = _next;
        random = _random;
    }
};

// 思路：三轮遍历
// 第一轮：把当前节点的拷贝作为当前节点的next
// 第二轮：把当前节点的random.next作为下一个节点的random
// 第三轮：从链表中恢复原来的链表，并取出拷贝的结果

class Solution {
    public Node copyRandomList(Node head) {
        if (head == null) return null;
        Node cur = head;
        // 1st round
        while (cur != null) {
            cur.next = new Node(cur.val, cur.next, null);
            cur = cur.next.next;
        }
        // 2nd round
        cur = head;
        while (cur != null) {
            if (cur.random != null) {
                cur.next.random = cur.random.next;
            }
            cur = cur.next.next;
        }
        // 3rd round
        cur = head;
        Node newHead = new Node(0, null, null), newListCur = newHead;
        while (cur != null) {
            newListCur.next = cur.next;
            cur.next = cur.next.next;
            cur = cur.next;
            newListCur = newListCur.next;
        }
        return newHead.next;
    }
}
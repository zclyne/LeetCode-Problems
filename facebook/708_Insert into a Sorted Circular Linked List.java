/*
// Definition for a Node.
class Node {
    public int val;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _next) {
        val = _val;
        next = _next;
    }
};
*/

// 方法：画个图，分别讨论cur.val < insertVal <= next.val
// 以及insertVal刚好需要插入在链表的最大值和最小值之前这两种情况
// 第一种情况对应于cur.val < insertVal && insertVal <= next.val
// 第二种情况对应于cur.val > next.val && (cur.val <= insertVal || insertVal <= next.val)
// 在没找到满足这两个条件的节点，且next != head之前，都不断向后移动cur和next指针
// 退出循环后，当前节点就应该插入在cur和next之间

class Solution {
    public Node insert(Node head, int insertVal) {
        Node node = new Node(insertVal);
        if (head == null) {
            node.next = node;
            return node;
        }
        Node cur = head, next = cur.next;
        while (next != head && !( cur.val < insertVal && insertVal <= next.val ||
        cur.val > next.val && (cur.val <= insertVal || insertVal <= next.val))) {
            cur = next;
            next = next.next;
        }
        cur.next = node;
        node.next = next;
        return head;
    }
}
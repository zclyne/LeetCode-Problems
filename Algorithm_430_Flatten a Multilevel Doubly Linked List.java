import java.util.*;

/*
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
};
*/

// 方法：遍历，并用栈记录child层级关系

class Solution {
    public Node flatten(Node head) {
        if (head == null) {
            return null;
        }
        Deque<Node> stack = new LinkedList<>();
        stack.push(head);
        Node dummyHead = new Node();
        Node prev = dummyHead;
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            prev.next = cur;
            cur.prev = prev;
            if (cur.next != null) {
                stack.push(cur.next);
            }
            if (cur.child != null) {
                stack.push(cur.child);
                cur.child = null;
            }
            prev = cur;
        }
        
        // disconnect from dummyHead
        head.prev = null;
        return head;
    }
}
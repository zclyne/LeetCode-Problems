/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/

// 思路：层序遍历，遍历的当前层的所有节点的next都已经设置完毕，所以可以直接用cur = cur.next来对这一层进行遍历
// 在遍历这一层的同时，将这层的下一层的所有节点连接起来，用nextLevelPre来记录下一层的上一个节点

class Solution {
    public Node connect(Node root) {
        Node cur = root, dummy = new Node(0), nextLevelPre = dummy;
        while (cur != null) {
            if (cur.left != null) {
                nextLevelPre.next = cur.left;
                nextLevelPre = nextLevelPre.next;
            }
            if (cur.right != null) {
                nextLevelPre.next = cur.right;
                nextLevelPre = nextLevelPre.next;
            }
            if (cur.next != null) { // still has node in this level
                cur = cur.next;
            } else { // switch to the next level
                cur = dummy.next;
                dummy = new Node(0);
                nextLevelPre = dummy;
            }
        }
        return root;
    }
}
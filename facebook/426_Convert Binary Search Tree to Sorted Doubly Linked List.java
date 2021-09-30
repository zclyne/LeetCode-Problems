/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
};
*/

// 方法：用变量last记录当前节点所要连接的上一个节点
// 由于bst的中序遍历是有序的，所以要构成有序链表，必须采用中序遍历
// 先对root的左子树进行递归遍历，然后将root和last连接
// 即last.right = root, root.left = last
// 最后将root更新为last，然后对root的右子树递归遍历
// 遍历完成后，只剩下首尾两个节点还没有连接起来
// 因此从root开始，分别向左和向右到达最左、最后节点，并将它们连接起来
// 最左节点就是链表的头节点

class Solution {

    private Node last;

    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }
        
        inorder(root);

        // look for the head
        Node head = root;
        while (head.left != null) {
            head = head.left;
        }
        // look for the tail
        Node tail = root;
        while (tail.right != null) {
            tail = tail.right;
        }
        head.left = tail;
        tail.right = head;

        return head;
    }

    private void inorder(Node root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        if (last != null) {
            last.right = root;
            root.left = last;
        }
        last = root;
        inorder(root.right);
    }
}
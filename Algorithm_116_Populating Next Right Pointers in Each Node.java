import java.util.Queue;
import java.util.LinkedList;

class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val,Node _left,Node _right,Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};

// My Solution
// 类似于层序遍历，把每一层的最后一个节点的next置为null，其他节点的next置为层序遍历中的下一个节点，即队列的头部节点

class Solution {
    public Node connect(Node root) {
        if (root == null) return null;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node curNode = queue.poll();
                if (curNode == null) continue;
                curNode.next = i == size - 1 ? null : queue.peek();
                queue.offer(curNode.left);
                queue.offer(curNode.right);
            }
        }
        return root;
    }
}

// Iterative Solution
// 思路：layerHead记录每一层的头节点，cur记录在这层中当前正在访问的节点
// 把cur的left接到cur.right上，然后根据cur.next是否为null来处理cur.right.next
// 一层处理完后，layerHead = layerHead.next，表示移动到下一层

class IterativeSolution {
    public Node connect(Node root) {
        if (root == null) return null;
        Node layerHead = root, cur = null;
        while (layerHead.left != null) {
            cur = layerHead;
            while (cur != null) {
                cur.left.next = cur.right;
                cur.right.next = cur.next == null ? null : cur.next.left;
                cur = cur.next;
            }
            layerHead = layerHead.left;
        }
        return root;
    }
}

// Recursive Solution

class RecursiveSolution {
    public Node connect(Node root) {
        if (root == null) return null;
        if (root.left != null) root.left.next = root.right;
        if (root.right != null && root.next != null) root.right.next = root.next.left;
        connect(root.left);
        connect(root.right);
        return root;
    }
}
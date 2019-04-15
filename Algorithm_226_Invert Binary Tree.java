import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

// Solution 1: Recursive
// 思路：直接交换。
// 递归有可能溢出

class Solution {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode tempLeft = invertTree(root.right);
        root.right = invertTree(root.left);
        root.left = tempLeft;
        return root;
    }
}

// Solution 2: Stack
// 思路：用栈模拟递归的过程

class Solution2 {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode curRoot = stack.pop();
            TreeNode tempLeft = curRoot.left;
            curRoot.left = curRoot.right;
            curRoot.right = tempLeft;
            if (curRoot.left != null) {
                stack.push(curRoot.left);
            }
            if (curRoot.right != null) {
                stack.push(curRoot.right);
            }
        }
        return root;
    }
}

// Solution 3: BFS

class Solution3 {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()) {
            final TreeNode node = queue.poll();
            final TreeNode left = node.left;
            node.left = node.right;
            node.right = left;

            if(node.left != null) {
                queue.offer(node.left);
            }
            if(node.right != null) {
                queue.offer(node.right);
            }
        }
        return root;
    }
}
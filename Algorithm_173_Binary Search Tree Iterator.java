import java.util.Stack;

// 思路：用栈模拟中序遍历的过程
// 在初始化时，不断将root.left添加到栈中，直到root.left == null为止
// 调用next()时，从栈顶弹出一个元素，这个元素就是要返回的值
// 在返回之前，将这个元素的右子树以同样的方式，不管将left添加到栈中直到为null

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class BSTIterator {
    private Stack<TreeNode> stack = new Stack<>();
    public BSTIterator(TreeNode root) {
        while (root != null) {
            stack.add(root);
            root = root.left;
        }
    }
    
    public int next() {
        TreeNode node = stack.pop();
        int result = node.val;
        TreeNode cur = node.right;
        while (cur != null) {
            stack.add(cur);
            cur = cur.left;
        }
        return result;
    }
    
    public boolean hasNext() {
        return !stack.empty();
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
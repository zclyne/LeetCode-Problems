import java.util.Stack;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

 // Iterative Solution
 // 思路：类似于Algorithm_94，二叉树的中序遍历

class Solution {
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (pre != null && pre.val >= root.val) {
                return false;
            }
            pre = root;
            root = root.right;
        }
        return true;
    }
}

// Recursive Solution
// 必须使用Long而不能使用Integer

// public class Solution {
//     public boolean isValidBST(TreeNode root) {
//         return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
//     }
    
//     public boolean isValidBST(TreeNode root, long minVal, long maxVal) {
//         if (root == null) return true;
//         if (root.val >= maxVal || root.val <= minVal) return false;
//         return isValidBST(root.left, minVal, root.val) && isValidBST(root.right, root.val, maxVal);
//     }
// }
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.swing.tree.TreeNode;

/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode() {} TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) { this.val = val; this.left
 * = left; this.right = right; } }
 */

// 递归解法

class Solution {
    private List<Integer> result = new ArrayList<>();
    public List<Integer> preorderTraversal(TreeNode root) {
        preorder(root);
        return result;
    }
    private void preorder(TreeNode root) {
        if (root == null) {
            return;
        }
        result.add(root.val);
        preorder(root.left);
        preorder(root.right);
    }
}

// 迭代解法

class IterativerSolution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.empty()) {
            TreeNode cur = stack.pop();
            if (cur != null) {
                result.add(cur.val);
                stack.push(cur.right);
                stack.push(cur.left);
            }
        }
        return result;
    }
}
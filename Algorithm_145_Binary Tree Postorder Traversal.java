import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.swing.tree.TreeNode;

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

// 递归解法

class Solution {
    private List<Integer> result = new ArrayList<>();
    public List<Integer> postorderTraversal(TreeNode root) {
        postorder(root);
        return result;
    }
    private void postorder(TreeNode root) {
        if (root == null) {
            return;
        }
        postorder(root.left);
        postorder(root.right);
        result.add(root.val);
    }
}

// 迭代解法
// 思路：用另一个栈来存储当前节点的访问次数
// 如果是第一次访问，则将当前节点的左子节点压栈
// 如果是第二次访问，则将当前节点的右子节点压栈
// 如果是第三次访问，则输出当前节点本身的值

class IterativeSolution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        Stack<Integer> visitCountStack = new Stack<>();
        stack.push(root);
        visitCountStack.push(0);
        while (!stack.empty()) {
            TreeNode cur = stack.pop();
            int curCount = visitCountStack.pop();
            if (cur != null) {
                if (curCount == 0) { // visit for the first time
                    stack.push(cur);
                    visitCountStack.push(1);
                    if (cur.left != null) {
                        stack.push(cur.left);
                        visitCountStack.push(0);
                    }
                } else if (curCount == 1) { // visit for the second time
                    stack.push(cur);
                    visitCountStack.push(2);
                    if (cur.right != null) {
                        stack.push(cur.right);
                        visitCountStack.push(0);
                    }
                } else { // visit for the third time, output itself
                    result.add(cur.val);
                }
            }
        }
        return result;
    }
}

// 第三种解法：按照先根节点、后右子树、最后左子树（即类似于前序遍历）的方式遍历树，并保存结果
// 然后把结果逆序输出，就是后序遍历的结果
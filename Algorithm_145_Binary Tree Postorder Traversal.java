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

// 更好的迭代解法
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null) {
            return res;
        }

        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        TreeNode prev = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop(); // 此时，root的左子树已经被遍历完毕
            if (root.right == null || root.right == prev) { // root.right == prev表示root的右子树已经被遍历完成，现在应该访问root本身
                res.add(root.val);
                prev = root;
                root = null;
            } else { // 先遍历root的右子树
                stack.push(root);
                root = root.right;
            }
        }
        return res;
    }
}

// 第三种解法：按照先根节点、后右子树、最后左子树（即类似于前序遍历）的方式遍历树，并保存结果
// 然后把结果逆序输出，就是后序遍历的结果

// 解法4：Morris遍历
// https://leetcode-cn.com/problems/binary-tree-postorder-traversal/solution/er-cha-shu-de-hou-xu-bian-li-by-leetcode-solution/
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null) {
            return res;
        }

        TreeNode p1 = root, p2 = null;

        while (p1 != null) {
            p2 = p1.left;
            if (p2 != null) {
                while (p2.right != null && p2.right != p1) {
                    p2 = p2.right;
                }
                if (p2.right == null) {
                    p2.right = p1;
                    p1 = p1.left;
                    continue;
                } else {
                    p2.right = null;
                    addPath(res, p1.left);
                }
            }
            p1 = p1.right;
        }
        addPath(res, root);
        return res;
    }

    public void addPath(List<Integer> res, TreeNode node) {
        int count = 0;
        while (node != null) {
            ++count;
            res.add(node.val);
            node = node.right;
        }
        int left = res.size() - count, right = res.size() - 1;
        while (left < right) {
            int temp = res.get(left);
            res.set(left, res.get(right));
            res.set(right, temp);
            left++;
            right--;
        }
    }
}
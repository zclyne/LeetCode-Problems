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

// 思路：直接递归做，变量result记录最终的结果，curVal记录当前已经访问过的path的数值
// 若当前root是空，直接返回
// 若当前root非空，则curVal = curVal * 10 + root.val，把root算到当前path中
// 若当前root是叶子节点，直接更新result并返回
// 若当前root不是叶子节点，则对左右子节点分别递归

class Solution {
    private int result;
    public int sumNumbers(TreeNode root) {
        helper(root, 0);
        return result;
    }
    private void helper(TreeNode root, int curVal) {
        if (root == null) {
            return;
        }
        curVal = curVal * 10 + root.val;
        if (root.left == null && root.right == null) { // root is leaf
            result += curVal;
            return;
        }
        helper(root.left, curVal);
        helper(root.right, curVal);
    }
}
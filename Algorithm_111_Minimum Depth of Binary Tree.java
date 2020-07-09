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

// 思路：直接递归，用变量minDepth来记录最小深度值，递归中每当遇到叶子节点时，就尝试更新minDepth

class Solution {

    private int minDepth = Integer.MAX_VALUE;

    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        getDepth(root, 1);
        return minDepth;
    }

    private void getDepth(TreeNode root, int curDepth) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) { // root is leaf
            minDepth = Math.min(minDepth, curDepth);
        }
        getDepth(root.left, curDepth + 1);
        getDepth(root.right, curDepth + 1);
    }

}
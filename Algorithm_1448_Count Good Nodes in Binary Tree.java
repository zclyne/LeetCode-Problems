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

// 方法：前序遍历，遍历过程中记录当前路径上最大的节点值
// 如果路径上的最大值小于等于当前节点值，则result++

class Solution {
    private int result = 0;
    public int goodNodes(TreeNode root) {
        dfs(root, Integer.MIN_VALUE);
        return result;
    }

    private void dfs(TreeNode root, int maxOnPath) {
        if (root == null) {
            return;
        }
        if (maxOnPath <= root.val) {
            result++;
        }
        maxOnPath = Math.max(root.val, maxOnPath);
        dfs(root.left, maxOnPath);
        dfs(root.right, maxOnPath);
    }
}
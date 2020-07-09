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

// 思路：直接根据左右子树的高度差来判断是否是一颗平衡二叉树

class Solution {

    // stores the maximum height difference between the left sub-tree and the right sub-tree of all nodes in the tree
    private int maxHeightDiff = 0;

    public boolean isBalanced(TreeNode root) {
        getHeight(root);
        return maxHeightDiff <= 1;
    }

    private int getHeight(TreeNode root) {
        // if maxHeightDiff > 1, we've already proved that the tree is not balanced, so we don't need to
        // check root, just return 0 won't affect the result
        if (maxHeightDiff > 1 || root == null) {
            return 0;
        }
        int leftHeight = getHeight(root.left), rightHeight = getHeight(root.right);
        maxHeightDiff = Math.max(maxHeightDiff, Math.abs(leftHeight - rightHeight));
        return Math.max(leftHeight, rightHeight) + 1;
    }

}
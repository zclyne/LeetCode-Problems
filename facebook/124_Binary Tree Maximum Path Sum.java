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
class Solution {

    private int result = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        helper(root);
        return result;
    }

    private int helper(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftPathSum = helper(root.left);
        int rightPathSum = helper(root.right);
        result = Math.max(result, Math.max(Math.max(root.val, root.val + leftPathSum + rightPathSum),
                            Math.max(root.val + leftPathSum, root.val + rightPathSum)));
        return Math.max(root.val, Math.max(root.val + leftPathSum, root.val + rightPathSum));
    }
}
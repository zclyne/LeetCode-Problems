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

    private TreeNode last;

    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        last = new TreeNode(0);
        helper(root);
    }

    private void helper(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode right = root.right;
        last.right = root;
        last = root;
        helper(root.left);
        root.left = null;
        helper(right);
    }
}
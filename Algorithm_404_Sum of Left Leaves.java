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

    private int result = 0;

    public int sumOfLeftLeaves(TreeNode root) {
        preOrder(root, false); // the root itself is not considered as a leaf
        return result;
    }

    private void preOrder(TreeNode root, boolean goLeft) {
        if (root.left == null && root.right == null && goLeft) { // is left leaf
            result += root.val;
            return;
        }
        if (root.left != null) {
            preOrder(root.left, true);
        }
        if (root.right != null) {
            preOrder(root.right, false);
        }
    }
}
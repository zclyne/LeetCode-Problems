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
    public int findSecondMinimumValue(TreeNode root) {
        return helper(root, root.val);
    }

    private int helper(TreeNode cur, int rootValue) {
        if (cur.val != rootValue) {
            return cur.val;
        }
        if (cur.left == null) {
            return -1;
        }
        int leftVal = helper(cur.left, rootValue);
        int rightVal = helper(cur.right, rootValue);
        if (leftVal == -1) {
            return rightVal;
        } else if (rightVal == -1) {
            return leftVal;
        } else {
            return Math.min(leftVal, rightVal);
        }
    }
}
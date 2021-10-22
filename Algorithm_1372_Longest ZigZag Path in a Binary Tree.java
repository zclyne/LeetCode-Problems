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

// 方法：对二叉树前序遍历，返回值是一个int[2]，第一个值表示向左走所能得到的最长的path长度
// 第二个值表示向右走所能得到的最长的path长度

class Solution {

    private int result = 0;

    public int longestZigZag(TreeNode root) {
        traverse(root);
        return result;
    }

    private int[] traverse(TreeNode root) {
        if (root == null) {
            return new int[2];
        }

        int leftLength = 0, rightLength = 0;
        if (root.left != null) {
            int[] leftRes = traverse(root.left);
            leftLength = 1 + leftRes[1];
        }
        if (root.right != null) {
            int[] rightRes = traverse(root.right);
            rightLength = 1 + rightRes[0];
        }
        result = Math.max(result, Math.max(leftLength, rightLength));
        return new int[]{leftLength, rightLength};
    }
}
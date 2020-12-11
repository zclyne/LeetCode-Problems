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

// 思路：若root是null，直接返回false；若root是叶子节点，判断root.val是否等于sum
// 若root不是叶子节点，则对其左右子树做递归调用

class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        } else if (root.left == null && root.right == null) {
            return root.val == sum;
        } else {
            return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
        }
    }
}
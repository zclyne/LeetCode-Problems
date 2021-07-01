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

// 思路：类成员变量result记录最终的答案，私有方法helper输入一个节点root，并返回
// 以这个节点为起始节点的一个path的和的最大值
// 对于root，其左右子节点的path的和的最大值分别为leftSum和rightSum，则共有三种情况
// 1. leftSum + rightSum + root.val是一个新的path的和的最大值
// 2. leftSum + root.val是一个新的path的和的最大值
// 3. rightSum + root.val是一个新的path的和的最大值
// 4. root.val是一个新的path的和的最大值
// 后面3种情况统一到变量maxSumStartWithRoot上，并作为返回值
// 并且更新result

class Solution {
    private int result = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        helper(root);
        return result;
    }

    // helper returns the largest sum of path that starts with root
    private int helper(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftSum = helper(root.left);
        int rightSum = helper(root.right);
        int maxSumStartWithRoot = Math.max(0, Math.max(leftSum, rightSum)) + root.val;
        result = Math.max(result, Math.max(maxSumStartWithRoot, leftSum + rightSum + root.val));
        return maxSumStartWithRoot;
    }
}

// 二刷
class Solution {
    private class ReturnValue {
        public int max; // may or may not include root in the final path
        public int maxEndWithRoot; // max Sum of which the path ends with root
        ReturnValue(int max, int maxEndWithRoot) {
            this.max = max;
            this.maxEndWithRoot = maxEndWithRoot;
        }
    }

    public int maxPathSum(TreeNode root) {
        return this.traverse(root).max;
    }

    private ReturnValue traverse(TreeNode root) {
        if (root == null) {
            return new ReturnValue(Integer.MIN_VALUE, 0);
        }
        
        ReturnValue leftReturnValue = this.traverse(root.left);
        ReturnValue righReturnValue = this.traverse(root.right);

        int maxEndWithLeft = Math.max(leftReturnValue.maxEndWithRoot, 0); // 0 for not selecting the path in the left sub tree
        int maxEndWithRight = Math.max(righReturnValue.maxEndWithRoot, 0); // 0 for not selecting the path in the right sub tree
        int maxEndWithRoot = Math.max(maxEndWithLeft, maxEndWithRight) + root.val;

        // maxEndWithLeft + maxEndWithRight + root.val represents the path that includes root
        // leftReturnValue.max and rightReturnValue.max represent the paths that don't include root
        int max = Math.max(maxEndWithLeft + maxEndWithRight + root.val, Math.max(leftReturnValue.max, righReturnValue.max));

        return new ReturnValue(max, maxEndWithRoot);
    }
}
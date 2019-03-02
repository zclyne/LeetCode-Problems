// 思路：helper函数返回一个数组，设为info。info[0]保存的是以root为根节点的树的最大深度，info[1]保存的是以root为根节点的树的diameter
// 若root为null，返回{-1, 0}
// 对于diameter，有3种情况：
// 1. root在diameter的路径上，则diameter = 左子树深度（1 + leftInfo[0]） + 右子树深度（1 + rightInfo[0]）
// 2. diameter在左子树上，则info[1] = leftInfo[1]
// 3. diameter在右子树上，则info[1] = rightInfo[1]
// 三者最大值即为新的diameter

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class Solution {
    public int diameterOfBinaryTree(TreeNode root) {
        return helper(root)[1];
    }
    private int[] helper(TreeNode root) {
        if (root == null) {
            return new int[] {-1, 0};
        }
        int[] leftInfo = helper(root.left);
        int[] rightInfo = helper(root.right);
        int[] res = new int[2];
        res[0] = 1 + Math.max(leftInfo[0], rightInfo[0]); // max depth
        res[1] = Math.max(Math.max(leftInfo[1], rightInfo[1]), 1 + leftInfo[0] + 1 + rightInfo[0]); // longest path length
        return res;
    }
}
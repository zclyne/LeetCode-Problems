class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

// Better Solution
// 思路：helper(..)返回一个数组res，其中res[0]为root被rob时能得到的最多的钱，res[1]为root不被rob时能得到的最多的钱
// 其余思路与MySolution相同
// 由于返回数组的方法避免了分别考虑rob和不rob根节点时所产生的重复计算，因此速度很快

class Solution {
    
    public int rob(TreeNode root) {
        int[] res = helper(root);
        return Math.max(res[0], res[1]);
    }

    public int[] helper(TreeNode root) {
        if (root == null) {
            return new int[2];
        }
        int[] left = helper(root.left);
        int[] right = helper(root.right);
        int[] res = new int[2];
        res[0] = root.val + left[1] + right[1];
        res[1] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        return res;
    }

}


// My First Solution, beats 19.03%
// 思路：递归。robRoot(..) 和notRobRoot(..)分别返回选择rob根节点和不rob根节点时所能得到的最多的钱
// 若选择rob root，则一定不能rob root.left和root.right，返回root.val + notRobRoot(root.left) + notRobRoot(root.right)
// 若选择不rob root，则既可以rob左右子节点，也可以不rob左右子节点，要使得到的钱最多，返回Math.max(robRoot(root.left), notRobRoot(root.left)) + Math.max(robRoot(root.right), notRobRoot(root.right))
// 由于重复计算过多，因此速度很慢，可以通过使用HashMap存储来加速

class MySolution {

    public int rob(TreeNode root) {
        return Math.max(robRoot(root), notRobRoot(root));
    }

    private int robRoot(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return root.val + notRobRoot(root.left) + notRobRoot(root.right);
    }

    private int notRobRoot(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(robRoot(root.left), notRobRoot(root.left)) + Math.max(robRoot(root.right), notRobRoot(root.right));
    }

}
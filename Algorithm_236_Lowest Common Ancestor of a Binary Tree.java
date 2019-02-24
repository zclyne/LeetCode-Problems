// 思路：当root为p或q中的一个时就返回root。不妨设root为p，则有2种情况：
// 1. q在以root为根节点的树中，则此时root就是答案
// 2. q不在以root为根节点的树中
// 对于第一种情况，对于任何root之前的节点调用LowestCommonAncestor，left和right中总是只有包含root的那一个不为null，另一个一定为null
// 因此返回值总是包含root的那一边，直到到root本身为止。因此返回值就是root，正确
// 对于第二种情况，对于root之前的节点调用lowestCommonAncestor，若p和q分别在该节点的左右两子树中，则left和right都不会是null，因此返回值是当前节点，记为newRoot
// 对于newRoot之前的节点调用lowestCommonAncestor，此时p和q都在包含了newRoot的那一棵子树中，因此left和right中又只有一边不为null而另一边一定为null
// 因此返回值是非null的那边，即newRoot，正确

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        return left != null && right != null ? root : left == null ? right : left; 
    }
}
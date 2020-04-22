// 思路：直接用两个变量parent和grandParent来保存上一个和上上个节点

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class Solution {
    private int result = 0;
    public int sumEvenGrandparent(TreeNode root) {
        if (root == null) {
            return result;
        }
        preOrder(root, null, null);
        return result;
    }
    private void preOrder(TreeNode root, TreeNode parent, TreeNode grandParent) {
        if (root == null) {
            return;
        }
        if (grandParent != null && grandParent.val % 2 == 0) {
            result += root.val;
        }
        preOrder(root.left, root, parent);
        preOrder(root.right, root, parent);
    }
}
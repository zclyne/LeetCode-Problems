class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

// 思路：BST的中序遍历就是从小到大输出所有元素的值
// 用变量count记录已经访问过的结点数量，若count达到k，则此时的根节点就是要求的第k小的节点

class Solution {

    private int count = 0;
    private int result = 0;

    public int kthSmallest(TreeNode root, int k) {
        inorder(root, k);
        return result;
    }

    private void inorder(TreeNode root, int k) {
        if (root == null) {
            return;
        }
        inorder(root.left, k);
        if (++count == k) {
            result = root.val;
            return;
        }
        inorder(root.right, k);
    }

}
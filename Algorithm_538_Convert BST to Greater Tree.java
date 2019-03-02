// 思路：变量sum存储所有比root大的节点值之和
// 由于BST的右子树所有节点值全部大于root，因此先对右子树递归
// 对右子树的递归完成后，把sum加到当前root.val上，并把新的root.val作为新的sum
// 由于左子树所有节点值全部小于root，因此以上操作完成后再对左子树递归

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class Solution {
    
    private int sum = 0;
    
    public TreeNode convertBST(TreeNode root) {
        helper(root);
        return root;
    }

    public void helper(TreeNode root) {
        if (root == null) {
            return;
        }
        helper(root.right);
        root.val += sum;
        sum = root.val;
        helper(root.left);
    }

}
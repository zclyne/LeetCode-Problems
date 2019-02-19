// 思路：在inorder中找到根节点的下标，然后用inorder的左半边子数组和右半边子数组分别构建root的左子树和右子树
// Arrays.copyOfRange()比较费时，可以用传参方式代替
// 可以用map把inorder中元素对应的下标存下来，进一步节省时间

import java.util.Arrays;
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        int rootIdx = 0;
        for (; rootIdx < inorder.length; rootIdx++) {
            if (inorder[rootIdx] == preorder[0]) { // find the root's index in inorder
                break;
            }
        }
        TreeNode root = new TreeNode(preorder[0]);
        root.left = buildTree(Arrays.copyOfRange(preorder, 1, rootIdx + 1), Arrays.copyOfRange(inorder, 0, rootIdx));
        root.right = buildTree(Arrays.copyOfRange(preorder, rootIdx + 1, preorder.length), Arrays.copyOfRange(inorder, rootIdx + 1, inorder.length));
        return root;
    }
}
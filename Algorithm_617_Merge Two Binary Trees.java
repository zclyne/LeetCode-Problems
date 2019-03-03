class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

// 思路：以树t1为基准，把t2合并到t1上
// 若当前t2为null，则整个以t1为根节点的树都不需要改变，直接返回t1
// 若此时t1为null，经过上一步判断后，当前t2已经保证不是null，则需要创建新的t1节点，其初始值为0
// 把t2.val加到t1上
// 对left和right递归调用mergeTrees(..)，返回值作为t1.left和t1.right
// 最后返回t1

class Solution {
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t2 == null) {
            return t1;
        }
        if (t1 == null) {
            t1 = new TreeNode(0);
        }
        t1.val += t2.val;
        t1.left = mergeTrees(t1.left, t2.left);
        t1.right = mergeTrees(t1.right, t2.right);
        return t1;
    }
}
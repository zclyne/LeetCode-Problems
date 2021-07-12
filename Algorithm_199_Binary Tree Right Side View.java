import java.util.ArrayList;
import java.util.List;

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

// 方法：用变量depth存储当前的深度
// 如果深度 == result.size()，说明是第一次访问到这个深度的节点
// 由于递归时，优先递归右子树，所以能够保证第一次访问到这个深度时，此时的节点就是这个深度下最右边的节点
// 将其添加到result中

class Solution {

    private List<Integer> result;

    public List<Integer> rightSideView(TreeNode root) {
        result = new ArrayList<>();
        traverse(root, 0);
        return result;
    }

    private void traverse(TreeNode root, int depth) {
        if (root == null) {
            return;
        }
        if (depth == result.size()) {
            result.add(root.val);
        }
        traverse(root.right, depth + 1);
        traverse(root.left, depth + 1);
    }
}
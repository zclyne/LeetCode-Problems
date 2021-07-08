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

// 方法：DFS遍历二叉树，如果当前节点是叶子结点，就把path加入到result中

class Solution {

    private List<String> result;

    public List<String> binaryTreePaths(TreeNode root) {
        result = new ArrayList<>();
        if (root != null) {
            traverse(root, "");
        }
        return result;
    }

    private void traverse(TreeNode root, String path) {
        if (path.length() != 0) {
            path += "->";
        }
        path += root.val;
        if (root.left == null && root.right == null) { // root is a leaf node
            result.add(path);
            return;
        }
        if (root.left != null) {
            traverse(root.left, path);
        }
        if (root.right != null) {
            traverse(root.right, path);
        }
    }
}
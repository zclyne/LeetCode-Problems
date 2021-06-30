import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// 解法：DFS
// 类似于二叉树的前序遍历

public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder stringBuilder = new StringBuilder();
        this.dfs(root, stringBuilder);
        return stringBuilder.toString();
    }

    private void dfs(TreeNode root, StringBuilder stringBuilder) {
        if (stringBuilder.length() > 0) {
            stringBuilder.append(",");
        }
        if (root == null) {
            stringBuilder.append("null");
            return;
        }
        stringBuilder.append(root.val);
        this.dfs(root.left, stringBuilder);
        this.dfs(root.right, stringBuilder);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] nodes = data.split(",");
        Queue<String> nodesQueue = new LinkedList<>(Arrays.asList(nodes));
        return recoverTree(nodesQueue);
    }

    private TreeNode recoverTree(Queue<String> nodesQueue) {
        if (nodesQueue.isEmpty()) {
            return null;
        }
        String nodeValStr = nodesQueue.poll();
        if (nodeValStr == null || nodeValStr.equals("") || nodeValStr.equals("null")) {
            return null;
        }
        int val = Integer.parseInt(nodeValStr);
        TreeNode node = new TreeNode(val);
        node.left = this.recoverTree(nodesQueue);
        node.right = this.recoverTree(nodesQueue);
        return node;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

// BFS
// 用map存储列号到结果的映射
// 由于深度小的节点要排在深度大的节点之前，所以使用BFS

class Solution {

    class NodeWithCol {
        public TreeNode node;
        public int column;
        public NodeWithCol(TreeNode node, int column) {
            this.node = node;
            this.column = column;
        }
    }

    public List<List<Integer>> verticalOrder(TreeNode root) {
        int minColumn = 0;
        int maxColumn = 0;
        Map<Integer, List<Integer>> map = new HashMap<>();

        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        // BFS
        Deque<NodeWithCol> deque = new LinkedList<>();
        deque.offerLast(new NodeWithCol(root, 0));

        while (!deque.isEmpty()) {
            NodeWithCol nodeWithCol = deque.pollFirst();
            int curCol = nodeWithCol.column;
            minColumn = Math.min(minColumn, curCol);
            maxColumn = Math.max(maxColumn, curCol);
            List<Integer> list = map.getOrDefault(curCol, new ArrayList<>());
            list.add(nodeWithCol.node.val);
            map.put(curCol, list);
            if (nodeWithCol.node.left != null) {
                deque.offerLast(new NodeWithCol(nodeWithCol.node.left, curCol - 1));
            }
            if (nodeWithCol.node.right != null) {
                deque.offerLast(new NodeWithCol(nodeWithCol.node.right, curCol + 1));
            }
        }

        for (int i = minColumn; i <= maxColumn; i++) {
            if (map.containsKey(i)) {
                result.add(map.get(i));
            }
        }

        return result;
    }
}
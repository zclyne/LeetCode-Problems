import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

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

// 方法：用一个嵌套的TreeMap
// 外层的key是列的序号col，value是一个TreeMap
// 这个TreeMap的key是行号row，value是一个list，表示坐标为(row, col)的所有节点
// 对树进行前序遍历，遍历过程中构建map
// 遍历完成后，对外层TreeMap进行遍历
// treeMap的性质保证了遍历的顺序是按照key从小到的顺序
// 对于当前列对应的treeMap，再对其进行遍历。这个遍历是按照行号从小到大的顺序
// 对于当前(row, col)的所有节点nodes，将其排序后添加到这一列对应的所有节点的列表nodesForCol中
// 等这一列的所有行都遍历完成后，将nodesForCol添加到result中
// 最终result里保存的就是结果

class Solution {

    private TreeMap<Integer, TreeMap<Integer, List<Integer>>> map;

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        this.map = new TreeMap<>();
        traverse(root, 0, 0);
        for (TreeMap<Integer, List<Integer>> mapForCol : map.values()) {
            List<Integer> nodesForCol = new ArrayList<>();
            for (List<Integer> nodes : mapForCol.values()) {
                Collections.sort(nodes);
                nodesForCol.addAll(nodes);
            }
            result.add(nodesForCol);
        }
        return result;
    }

    private void traverse(TreeNode root, int row, int col) {
        if (root == null) {
            return;
        }
        TreeMap<Integer, List<Integer>> mapForCol = this.map.getOrDefault(col, new TreeMap<>());
        List<Integer> nodesForRow = mapForCol.getOrDefault(row, new ArrayList<>());
        nodesForRow.add(root.val);
        mapForCol.put(row, nodesForRow);
        this.map.put(col, mapForCol);
        traverse(root.left, row + 1, col - 1);
        traverse(root.right, row + 1, col + 1);
    }
}
// 思路：用队列处理，首先将根节点添加到队列中，开始循环
// 用变量size记录当前队列的大小，并从队列的头部取出size个节点
// 判断当前节点是否为空，若不为空，则把节点的val添加到curRes中，并把该节点的left和right添加到队列尾部
// 内部循环完成后，把curRes添加到res中，添加之前要判断curRes是否为空，如果不做这部判断，可能会把空List添加到res里

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

import java.util.Queue;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        List<Integer> curRes;
        while (!queue.isEmpty()) {
            curRes = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode newNode = queue.poll();
                if (newNode != null) {
                    curRes.add(newNode.val);
                    queue.offer(newNode.left);
                    queue.offer(newNode.right);
                }
            }
            if (curRes.size() > 0) { // this if statement is important because without this if, the last level with no elements will be added to res
                res.add(new ArrayList<Integer>(curRes));
            }
        }
        return res;
    }
}
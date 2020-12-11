import java.util.ArrayList;

// 思路：直接DFS，先按照从上到下的层序遍历存储到reversedResult中，遍历完成后再将其逆序
// 也可以用BFS，用栈的方式来实现层序遍历

class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        List<List<Integer>> reversedResult = new ArrayList<>();
        levelOrderTraversal(root, 0, reversedResult);
        for (int level = reversedResult.size() - 1; level >= 0; level--) {
            result.add(reversedResult.get(level));
        }
        return result;
    }

    private void levelOrderTraversal(TreeNode root, int curLevel, List<List<Integer>> reversedResult) {
        if (root == null) {
            return;
        }
        List<Integer> curLevelList;
        if (curLevel == reversedResult.size()) {
            curLevelList = new ArrayList<>();
        } else {
            curLevelList = reversedResult.get(curLevel);
        }
        curLevelList.add(root.val);
        if (curLevel == reversedResult.size()) {
            reversedResult.add(curLevelList);
        }
        levelOrderTraversal(root.left, curLevel + 1, reversedResult);
        levelOrderTraversal(root.right, curLevel + 1, reversedResult);
    }
}

// BFS Solution

public class BFSSolution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        List<List<Integer>> result = new LinkedList<List<Integer>>();
        
        if(root == null) {
            return result;
        }
        
        queue.offer(root);
        while(!queue.isEmpty()) {
            int curLevel = queue.size();
            List<Integer> curLevelList = new LinkedList<Integer>();
            for(int i = 0; i < curLevel; i++) {
                if(queue.peek().left != null) {
                    queue.offer(queue.peek().left);
                }
                if(queue.peek().right != null) {
                    queue.offer(queue.peek().right);
                }
                curLevelList.add(queue.poll().val);
            }
            result.add(0, curLevelList);
        }
        return result;
    }
}
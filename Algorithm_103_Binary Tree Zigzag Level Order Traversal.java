import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

// My Solution
// 思路：用变量flag记录当前应该正序还是逆序，每次遍历完当前层，即将进入遍历下一层时，就把flag反转。flag为true表示正序遍历，为false表示逆序遍历
// reverse方法负责把List中的元素反转

class Solution {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        boolean flag = true;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> curLayer = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode curNode = queue.poll();
                if (curNode == null) continue;
                curLayer.add(curNode.val);
                queue.offer(curNode.left);
                queue.offer(curNode.right);
            }
            if (!flag) reverse(curLayer);
            if (curLayer.size() > 0) res.add(curLayer);
            flag = !flag;
        }
        return res;
    }

    private void reverse(List<Integer> arr) {
        for (int i = 0; i < arr.size() / 2; i++) {
            int tmp = arr.get(i);
            arr.set(i, arr.get(arr.size() - i - 1));
            arr.set(arr.size() - i - 1, tmp);
        }
    }

}

// LinkedList Solution
// 思路：利用链表的在头部插入时间复杂度为O(1)的性质

class LinkedListSolution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        boolean flag = true;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> curLayer = new LinkedList<>(); // use LinkedList instead of ArrayList
            for (int i = 0; i < size; i++) {
                TreeNode curNode = queue.poll();
                if (curNode == null) continue;
                if (flag) curLayer.add(curNode.val);
                else curLayer.add(0, curNode.val); // insert at the front of the list
                queue.offer(curNode.left);
                queue.offer(curNode.right);
            }
            if (curLayer.size() > 0) res.add(curLayer);
            flag = !flag;
        }
        return res;
    }
}
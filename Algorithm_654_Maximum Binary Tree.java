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

// 方法1：递归
// 用一个二维数组maxIndexes记录nums各个子数组中最大值的下标
// maxIndexes[i][j] = k的值就是nums[i ~ j]这个子数组中，最大值的下标为k，i <= k <= j
// maxIndexes的构建采用动态规划来完成。初始状态是maxIndexes[i][i] = i
// maxIndexes构建完成后，开始递归。递归函数helper记录当前子数组的左右边界start和end
// maxIndexes[start][end]就是这个子数组中的最大值的下标
// 将其对应的值作为根节点，然后再分别对左右两部分进行递归

class Solution {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        int n = nums.length;
        int[][] maxIndexes = new int[n][n];
        for (int i = 0; i < n; i++) {
            maxIndexes[i][i] = i;
        }
        for (int len = 2; len <= n; len++) {
            for (int start = 0; start + len - 1 < n; start++) {
                int end = start + len - 1;
                if (nums[end] > nums[maxIndexes[start][end - 1]]) {
                    maxIndexes[start][end] = end;
                } else {
                    maxIndexes[start][end] = maxIndexes[start][end - 1];
                }
            } 
        }

        return helper(nums, maxIndexes, 0, n - 1);
    }
    
    private TreeNode helper(int[] nums, int[][] maxIndexes, int start, int end) {
        if (end < start) {
            return null;
        }
        if (end == start) {
            return new TreeNode(nums[start]);
        }
        int maxIndex = maxIndexes[start][end];
        TreeNode root = new TreeNode(nums[maxIndex]);
        root.left = helper(nums, maxIndexes, start, maxIndex - 1);
        root.right = helper(nums, maxIndexes, maxIndex + 1, end);
        return root;
    }
}

// 方法2：单调栈，时间复杂度为O(n)
// 维护一个单调递减的单调栈。以nums = [3, 2, 1, 6, 0, 5]为例
// 首先输入3，入栈
// 输入2，2 < 3，入栈
// 输入1，1 < 3，入栈
// 输入6，6 > 1。将1从栈中pop出来，新的栈顶元素为2，将2和6比较，2 < 6，所以1是2的子节点。又由于1在2右侧，所以1是2的右子节点
// 继续比较栈顶元素2和6。2 < 6，将2从栈中pop出来，新的栈顶元素为3。3 < 6，所以2是3的子节点，又由于2在3右侧，所以2是3的右子节点
// 新的栈顶元素为3。3 < 6，将3从栈中pop出来，栈为空。所以3是6的子节点，又由于3在6左侧，所以3是6的左子节点
// 栈为空，将6入栈
// 输入0，0 < 6，入栈
// 输入5，5 > 0，将0从栈中pop出来，新的栈顶元素为6。5 < 6，所以0是5的子节点。又由于0在5左侧，所以0是5的左子节点
// 新的栈顶元素为6，5 < 6，将5入栈
// 循环结束，最后栈中元素为[6, 5]
// 栈中剩余的元素从栈底到栈顶递减，并且栈底的元素一定在栈顶元素左侧
// 所以依次从栈中pop出剩余元素。当前元素在新的栈顶元素右侧，所以是新的栈顶元素的右子节点
// 本质上是要把较小的值作为其左右两侧较大的值中的那个较小者的子节点，因为较大者在递归过程中会被优先选择到
// https://leetcode-cn.com/problems/maximum-binary-tree/solution/zui-da-er-cha-shu-by-leetcode/ 第一条评论

class Solution {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        Deque<TreeNode> stack = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            int cur = nums[i];
            TreeNode curNode = new TreeNode(cur);
            while (!stack.isEmpty() && stack.peek().val < cur) {
                TreeNode top = stack.pop();
                if (!stack.isEmpty() && stack.peek().val < cur) { // top should be the right subnode of the current top of stack
                    stack.peek().right = top;
                } else { // stack is empty or stack.peek().val > cur, top should be the left subnode of cur
                    curNode.left = top;
                }
            }
            stack.push(curNode);
        }
        TreeNode last = null;
        while (!stack.isEmpty()) {
            TreeNode top = stack.pop();
            top.right = last;
            last = top;
        }
        return last;
    }
}
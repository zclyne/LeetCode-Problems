import java.util.Stack;

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

// My Iterative Solution using stack
// 思路：用栈存储已经恢复出的树中的节点，并用变量curDepth记录上一个节点的深度
// 遍历字符串S，每次提取出一个深度（newDepth）和一个节点值信息（curVal）
// 若newDepth == curDepth + 1，表明新的节点是上一个节点的子节点
// 否则，从栈中popcurDepth - newDepth + 1次，此时栈顶节点就是当前节点的父节点
// 根据此父节点是否有左子节点来判断将当前节点插入到父节点的左子树还是右子树上
// 最后将当前节点push到栈中，并更新curDepth信息

class Solution {
    public TreeNode recoverFromPreorder(String S) {
        Stack<TreeNode> stack = new Stack<>();
        int curDepth = -1, newDepth = 0;
        int pos = 0;
        TreeNode dummy = new TreeNode(0);
        stack.push(dummy);
        while (pos < S.length()) {
            // get depth
            newDepth = 0;
            while (S.charAt(pos) == '-') {
                newDepth++;
                pos++;
            }
            // get node val
            int curVal = 0;
            while (pos < S.length() && S.charAt(pos) != '-') {
                curVal = curVal * 10 + (S.charAt(pos) - '0');
                pos++;
            }
            int popCount = curDepth - newDepth + 1;
            while (popCount > 0) {
                stack.pop();
                popCount--;
            }
            TreeNode curNode = stack.peek();
            TreeNode newNode = new TreeNode(curVal);
            if (curNode.left == null) {
                curNode.left = newNode;
            } else {
                curNode.right = newNode;
            }
            stack.push(newNode);
            curDepth = newDepth;
        }
        return dummy.left;
    }
}

// Recursive Solution
// 思路：depth记录期望的深度，若当前节点的深度和期望的深度相同
// 则继续遍历S并获得当前节点的值，然后向下一层继续递归
// 若当前节点的深度与期望的深度不同，则直接return null，忽略这种情况
// 这种情况下index不会被更新，因此递归会持续向上退出，直到depth == numDash的那一层递归时
// 程序才会继续向下执行

class RecursiveSolution {
    private int index = 0;
    public TreeNode recoverFromPreorder(String S) {
        return helper(S, 0);
    }
    private TreeNode helper(String s, int depth) {
        // get the number of dashes, which represents the depth of the current node
        int numDash = 0;
        while (index + numDash < s.length() && s.charAt(index + numDash) == '-') {
            numDash++;
        }
        if (numDash != depth) return null;
        // get the value of this node
        int next = index + numDash;
        while (next < s.length() && s.charAt(next) != '-') next++;
        int val = Integer.parseInt(s.substring(index + numDash, next));
        index = next;
        TreeNode root = new TreeNode(val);
        root.left = helper(s, depth + 1);
        root.right = helper(s, depth + 1);
        return root;
    }
}
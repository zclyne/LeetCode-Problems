import java.util.HashSet;
import java.util.Set;

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
class FindElements {
    private Set<Integer> set = new HashSet<>();
    
    public FindElements(TreeNode root) {
        helper(root, 0);
    }
    private void helper(TreeNode root, int curVal) {
        if (root == null) {
            return;
        }
        root.val = curVal;
        set.add(root.val);
        helper(root.left, 2 * curVal + 1);
        helper(root.right, 2 * curVal + 2);
    }
    
    public boolean find(int target) {
        return set.contains(target);
    }
}

/**
 * Your FindElements object will be instantiated and called as such:
 * FindElements obj = new FindElements(root);
 * boolean param_1 = obj.find(target);
 */
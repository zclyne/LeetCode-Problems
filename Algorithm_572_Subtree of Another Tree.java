class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

// Naive Solution
// 函数helper比较两棵树是否完全相等，其比较方法是：首先判断s和t是否存在null，若二者都为null则返回true，若一个为null而一个不为null则返回false
// 若二者都不为null，则返回s.val == t.val && helper(s.left, t.left) && helper(s.right, t.right)
// isSubtree()要考虑三种情况：
// 1. s、t的根节点重合，此时应返回helper(s, t)
// 2. t在s的左子树中，返回isSubtree(s.left, t)
// 3. t在s的右子树中，返回isSubtree(s.right, t)

class Solution {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null && t == null) {
            return true;
        } else if (s == null || t == null) {
            return false;
        }
        return helper(s, t) || isSubtree(s.left, t) || isSubtree(s.right, t);
    }
    public boolean helper(TreeNode s, TreeNode t) {
        if (s == null && t == null) {
            return true;
        } else if (s == null || t == null) {
            return false;
        }
        return s.val == t.val && helper(s.left, t.left) && helper(s.right, t.right);
    }
}

// Another Solution, can be optimized to O(m + n)
// 思路：将树转换成字符串表示，分别记为s_string和t_string
// 然后判断s_string中是否包含t_string
// 若包含，则t存在于s中，否则不存在
// java本身的.contains()方法不是线性的，但可以使用KMP算法来优化到线性时间复杂度

class LinearSolution {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        return serialize(s).contains(serialize(t)); // Java uses a naive contains algorithm so to ensure linear time, // replace with KMP algorithm
    }
    
    public String serialize(TreeNode root) {
        StringBuilder res = new StringBuilder();
        serialize(root, res);
        return res.toString();
    }
    
    private void serialize(TreeNode cur, StringBuilder res) {
        if (cur == null) {res.append(",#"); return;}
        res.append("," + cur.val);
        serialize(cur.left, res);
        serialize(cur.right, res);
    }
}
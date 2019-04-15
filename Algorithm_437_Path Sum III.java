import java.util.HashMap;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

// DFS Solution

// 思路：对于给定的根节点root，有2种情况：
// 1. 当前root不是路径的起始节点，考虑root.left和root.right，对这两个节点递归调用pathSum
// 2. 当前root是路径上的节点，对root调用helper
// helper的作用是假设当前根节点root是路径上的节点，比较root.val和sum，若相等，则当前root是路径的终止节点，这条路径满足条件
// 对root.left和root.right递归调用helper，并把新的sum设为sum - root.val，表示当前root不是路径上的终止节点，继续向下寻找

class Solution {

    public int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        return helper(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }

    public int helper(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        return (sum - root.val == 0 ? 1 : 0) +  helper(root.left, sum - root.val) + helper(root.right, sum - root.val);
    }

}



// HashMap Solution
// 思路：类似于从数组中找子数组的个数，使得子数组中所有元素之和为target：https://www.geeksforgeeks.org/number-subarrays-sum-exactly-equal-k/
// 由于树的递归，在递归完成后要把map中currSum对应的次数 - 1

class MapSolution {

    public int pathSum(TreeNode root, int sum) {
        HashMap<Integer, Integer> preSum = new HashMap<>();
        preSum.put(0,1);
        return helper(root, 0, sum, preSum);
    }
    
    public int helper(TreeNode root, int currSum, int target, HashMap<Integer, Integer> preSum) {
        if (root == null) {
            return 0;
        }
        
        currSum += root.val;
        int res = preSum.getOrDefault(currSum - target, 0);
        preSum.put(currSum, preSum.getOrDefault(currSum, 0) + 1);
        
        res += helper(root.left, currSum, target, preSum) + helper(root.right, currSum, target, preSum);
        preSum.put(currSum, preSum.get(currSum) - 1); // *important. remove the current path from the map
        return res;
    }

}
// 思路：dp[i]存储总结点个数为i时的unique bst个数
// 对于给定n，设根节点为i，则左子树包含[1, i - 1]，右子树包含[i + 1, n]
// 实际的节点值不影响答案，影响答案的仅仅是节点个数。因此右子树的unique bst个数就等于dp[n - i]
// 左子树的unique bst个数×右子树的unique bst个数就是以i为根节点的总共的unique bst个数
// 为处理子树为空的情况，设dp[0] = 1

class Solution {

    int[] dp;

    public int numTrees(int n) {
        dp = new int[n + 1];
        dp[0] = dp[1] = 1;
        return search(n);
    }

    public int search(int numOfNodes) {
        if (dp[numOfNodes] > 0) { // already calculated the result
            return dp[numOfNodes];
        }
        int sum = 0;
        for (int i = 1; i <= numOfNodes; i++) {
            sum += search(i - 1) * search(numOfNodes - i);
        }
        dp[numOfNodes] = sum;
        return sum;
    }
}
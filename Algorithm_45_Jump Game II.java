import java.util.Arrays;

// Better Solution

// 思路：类似于BFS，curFarthest记录当前能到达的最远的下标
// curEnd记录以当前的jump次数所能到达的最大下标
// res记录jump次数，相当于BFS的层数
// 对于当前的res，max(curFarthest, i + nums[i])表示下一层所能到达的最大下标
// 若i == curEnd，表示这一层已经遍历完毕，即将进入下一层，因此要更新res和curEnd
// 注意循环变量i的上界为nums.length - 1 (exclusive)

class Solution {
    public int jump(int[] nums) {
        int res = 0, curFarthest = 0, curEnd = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            curFarthest = Math.max(curFarthest, i + nums[i]);
            if (i == curEnd) {
                res++;
                curEnd = curFarthest;
            }
        }
        return res;
    }
}

// My solution
// DP. Very slow

class MySolution {
    public int jump(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, Integer.MAX_VALUE); // Integer.MAX_VALUE represents that nums[i] is not reached yet
        dp[0] = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j <= Math.min(i + nums[i], nums.length - 1); j++) {
                dp[j] = Math.min(dp[j], dp[i] + 1);
            }
        }
        return dp[nums.length - 1];
    }
}
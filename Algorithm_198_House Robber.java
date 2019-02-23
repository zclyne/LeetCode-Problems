// 思路：动态规划。prevNo、prevYes分别记录先前相邻的house没有被rob和被rob时可以得到的最多的钱
// curNo记录当前nums[i]没有被rob时可以得到的最多的钱，则curNo = max(prevNo, prevYes)
// 若rob nums[i]，则curYes = prevNo + num
// 向后移动到nums[i + 1]，并把curNo赋给prevNo，curYes赋给prevYes

class Solution {
    public int rob(int[] nums) {
        int prevNo = 0, prevYes = 0;
        for (int num : nums) {
            int curNo = Math.max(prevYes, prevNo);
            int curYes = prevNo + num;
            prevNo = curNo;
            prevYes = curYes;
        }
        return Math.max(prevNo, prevYes);
    }
}
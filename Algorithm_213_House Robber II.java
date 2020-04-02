// 思路：首尾相连表明nums[0]和nums[nums.length - 1]中最多只能有一个被选中
// 因此可以看作2次Algorithm_198，没有环的情况
// 第一次为nums[0 : nums.length - 1]，第二次为nums[1 : nums.length]

class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        } else if (nums.length == 1) {
            return nums[0];
        }
        int lastRob = 0, lastNotRob = 0, result = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            int curRob = lastNotRob + nums[i];
            int curNotRob = Math.max(lastRob, lastNotRob);
            lastRob = curRob;
            lastNotRob = curNotRob;
        }
        result = Math.max(lastRob, lastNotRob);
        lastRob = 0;
        lastNotRob = 0;
        for (int i = 1; i < nums.length; i++) {
            int curRob = lastNotRob + nums[i];
            int curNotRob = Math.max(lastRob, lastNotRob);
            lastRob = curRob;
            lastNotRob = curNotRob;
        }
        result = Math.max(result, Math.max(lastRob, lastNotRob));
        return result;
    }
}
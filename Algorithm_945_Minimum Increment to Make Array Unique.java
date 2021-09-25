// 方法：用lastUsed记录上一次使用的数字，moveCount记录move的次数
// 如果当前num大于lastUsed，说明num不需要被increment
// 否则，需要把num增加到lastUsed + 1

class Solution {
    public int minIncrementForUnique(int[] nums) {
        Arrays.sort(nums);
        int lastUsed = -1;
        int moveCount = 0;
        for (int num : nums) {
            if (num <= lastUsed) {
                moveCount += (lastUsed + 1) - num;
                lastUsed++;
            } else {
                lastUsed = num;
            }
        }
        return moveCount;
    }
}
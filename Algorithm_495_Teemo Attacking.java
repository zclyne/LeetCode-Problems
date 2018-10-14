// 思路：直接按顺序遍历timeSeries数组，用变量recoverTime记录下受到第i次攻击后恢复的时间点
// 每次将timeSeries[i]与recoverTime比较
class Solution {
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        int len = timeSeries.length;
        if (len == 0) return 0;
        if (len == 1) return duration;
        int sumTime = 0, recoverTime = timeSeries[0] + duration;
        for (int i = 1; i < len; i++)
        {
            if (timeSeries[i] > recoverTime) sumTime += duration;
            else sumTime += timeSeries[i] - timeSeries[i - 1];
            recoverTime = timeSeries[i] + duration;
        }
        return sumTime + duration;
    }
}
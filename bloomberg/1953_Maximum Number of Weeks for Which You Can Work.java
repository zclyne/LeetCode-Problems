// 方法：贪心法
// 找到milestones中的最大值以及所有milestone的总和
// 设总和减去最大值得到的结果为rest
// 则有两种情况：
// 1. rest >= max
// 这种情况下，我们可以把rest中的各个project插到max对应的project形成的slot中
// 所以一定能完成所有project，sum就是所需的week数
// 2. rest < max
// 表明除了max对应的project以外，把其他project的milestone插入到max对应的project形成的slot之后
// 仍然有slot剩余。这些slot就导致无法把max对应的project里的所有milestone做完
// 所以答案是2 * rest + 1
// https://leetcode.com/problems/maximum-number-of-weeks-for-which-you-can-work/discuss/1375390/Python-Solution-with-detailed-explanation-and-proof-and-common-failure-analysis

class Solution {
    public long numberOfWeeks(int[] milestones) {
        long sum = 0;
        int max = 0;
        for (int milestone : milestones) {
            sum += milestone;
            max = Math.max(max, milestone);
        }
        long rest = sum - max;
        if (rest >= max) { // can fit in the slots
            return sum;
        }
        return 2 * rest + 1;
    }
}
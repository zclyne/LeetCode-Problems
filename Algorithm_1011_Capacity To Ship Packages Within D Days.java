import java.util.Arrays;

// 思路：二分查找。初始的左侧边界值为weights中最大的那个，右侧边界值为
// 所有weight之和。设当前迭代的maxWeight为left和right的中位数
// 然后遍历weights，daysNeed是当前maxWeight所需要的运完所有货物所需的天数
// curWeight是当天所运的货物总重量。
// 若当前maxWeight太小，遍历完成后，daysNeed会大于D，这时应把left收缩到maxWeight + 1
// 若当前maxWeight能满足要求，则right = maxWeight，但不是maxWeight - 1，因为当前maxWeight
// 有可能是最终的答案

class Solution {
    public int shipWithinDays(int[] weights, int D) {
        int left = 0, right = 0;
        for (int weight : weights) {
            left = Math.max(left, weight);
            right += weight;
        }
        while (left < right) {
            int maxWeight = left + (right - left) / 2;
            int daysNeed = 1, curWeight = 0;
            for (int weight : weights) {
                curWeight += weight;
                if (curWeight > maxWeight) {
                    daysNeed++;
                    curWeight = weight;
                }
            }
            if (daysNeed > D) { // maxWeight is too small
                left = maxWeight + 1;
            } else { // 
                right = maxWeight;
            }
        }
        return left;
    }
}
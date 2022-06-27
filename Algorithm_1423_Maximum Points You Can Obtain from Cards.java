// 从首尾总共移除k个card，就相当于保留len - k个card
// 滑动窗口，遍历cardPoints，找到长度为len - k并且所有元素之和最小的subarray
// 用cardPoints的所有元素之和减去该subarray的所有元素之和即为结果

class Solution {
    public int maxScore(int[] cardPoints, int k) {
        int totalSum = 0;
        for (int point : cardPoints) {
            totalSum += point;
        }

        int l = cardPoints.length - k;
        int curSum = 0;
        for (int i = 0; i < l; i++) {
            curSum += cardPoints[i];
        }

        int minSum = curSum;

        for (int i = l; i < cardPoints.length; i++) {
            curSum -= cardPoints[i - l];
            curSum += cardPoints[i];
            minSum = Math.min(minSum, curSum);
        }

        return totalSum - minSum;
    }
}
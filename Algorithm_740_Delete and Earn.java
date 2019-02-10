// 思路：把数组nums中的所有元素加到bucket中，每一个bucket中存储该num的总和，即num * num出现的次数
// 对每一个num，有选用该num和不选用该num两种情况。如果选用num，则一定不能选用num - 1
// 若不选用num，则num - 1是否被选用并不影响结果
// 因此，take[num] = skip[num - 1] + sums[num]，skip[num] = max(skip[num - 1], take[num - 1])
// 最后取二者中较大的即可

class Solution {
    public int deleteAndEarn(int[] nums) {
        int[] sums = new int[10001];
        int maxNum = 0;
        for (int num : nums) {
            sums[num] += num;
            maxNum = num > maxNum ? num : maxNum;
        }
        int lastTakeResult = 0, lastSkipResult = 0;
        for (int num = 1; num <= maxNum; num++) {
            int tempTakeResult = sums[num] + lastSkipResult;
            int tempSkipResult = Math.max(lastTakeResult, lastSkipResult);
            lastSkipResult = tempSkipResult;
            lastTakeResult = tempTakeResult;
        }
        return Math.max(lastSkipResult, lastTakeResult);
    }
}
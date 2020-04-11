// 思路：maxEndHere[i]记录以arr[i]结尾的subarray的所有元素之和的最大值
// maxStartHere[i]记录以arr[i]开头的subarray的所有元素之和的最大值
// 利用Algorithm_53中的思路计算maxStartHere和maxEndHere
// 注意计算maxEndHere的时候，同时计算result，这一步是考虑不删除任何element的情况
// 最后再遍历一遍，考虑删除第i个元素，则maxEndHere[i - 1] + maxStartHere[i + 1]
// 就代表第i个元素被删除之后subarray的所有元素之和的最大值
// 注意此处i从1开始，以arr.length - 1结尾，不需要考虑第一个元素和最后一个元素
// 这是因为这两种情况已经在第一个for循环中就已经覆盖到了

class Solution {
    public int maximumSum(int[] arr) {
        int[] maxStartHere = new int[arr.length], maxEndHere = new int[arr.length];
        maxStartHere[arr.length - 1] = arr[arr.length - 1];
        maxEndHere[0] = arr[0];
        maxStartHere[arr.length - 1] = arr[arr.length - 1];
        int result = arr[0];
        for (int i = 1; i < arr.length; i++) {
            maxEndHere[i] = Math.max(maxEndHere[i - 1] + arr[i], arr[i]);
            result = Math.max(result, maxEndHere[i]);
        }
        for (int i = arr.length - 2; i >= 0; i--) {
            maxStartHere[i] = Math.max(maxStartHere[i + 1] + arr[i], arr[i]);
        }
        for (int i = 1; i < arr.length - 1; i++) {
            result = Math.max(result, maxEndHere[i - 1] + maxStartHere[i + 1]);
        }
        return result;
    }
}
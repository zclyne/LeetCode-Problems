import java.util.Arrays;

// 思路：桶排序
// 设数组nums中最小的元素值为min，最大的元素值为max，数组长度为n
// 则排序后的nums中，相邻两数之差的最大值至少为ceil((max - min) / (n - 1))
// 在所有数平均分布时取到等号
// 以gap = ceil((max - min) / (n - 1))为间隔，设置n - 1个桶
// 第k个桶中放置在[min + k * gap, min + (k + 1) * p)这一区间内的所有数(k >= 0)
// 实际只需要记录每个桶中的数字的最小值和最大值，因为要寻找的结果一定是由
// 一个桶的最大值和与之相邻的另一个桶的最小值构成

class Solution {
    public int maximumGap(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        // get the max and min value in nums
        int max = nums[0], min = nums[0], n = nums.length;
        for (int num : nums) {
            max = Math.max(max, num);
            min = Math.min(min, num);
        }
        // put all nums into the buckets
        int gap = (int) Math.ceil(((double) (max - min)) / (n - 1));
        int[] bucketMax = new int[n - 1];
        int[] bucketMin = new int[n - 1];
        Arrays.fill(bucketMax, Integer.MIN_VALUE);
        Arrays.fill(bucketMin, Integer.MAX_VALUE);
        for (int num : nums) {
            if (num == min || num == max) { // min and max can be skipped without affecting the result
                continue;
            }
            int k = (num - min) / gap;
            bucketMax[k] = Math.max(bucketMax[k], num);
            bucketMin[k] = Math.min(bucketMin[k], num);
        }
        // look for the result
        int result = 0, previous = min;
        for (int i = 0; i < n - 1; i++) {
            if (bucketMax[i] == Integer.MIN_VALUE && bucketMin[i] == Integer.MAX_VALUE) { // empty bucket
                continue;
            }
            // min value in current bucket minus previous is the new gap between two buckets
            result = Math.max(result, bucketMin[i] - previous);
            // update previous with the max value in the current bucket
            previous = bucketMax[i];
        }
        // as max is not put into any bucket, we have to check this case manually here
        result = Math.max(result, max - previous);
        return result;
    }
}
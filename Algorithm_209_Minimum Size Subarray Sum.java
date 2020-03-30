// O(n) Solution 双指针
// left和right记录当前的subArray的左右端点位置，curSum记录当前subArray中所有元素之和
// 若curSum >= s，把left向右收缩，直到curSum < s为止
// 无论curSum和s的关系如何，right++都必须执行
// 该算法是O(n)时间复杂度，因为right和left最多都只遍历[0, nums.length - 1]这个区间一次
// left并非每次都从0重新开始，而是从上次结束的地方继续向右走

class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        int min = Integer.MAX_VALUE;
        int left = 0, right = 0;
        int curSum = 0;
        while (right < nums.length) {
            curSum += nums[right];
            if (curSum >= s) {
                while (left <= right && curSum >= s) {
                    curSum -= nums[left++];
                }
                min = Math.min(min, right - left + 2);
            }
            right++;
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }
}

// O(nlogn) Solution 二分查找
// 由于所有元素都为正数，因此从左到右所有元素之和必然递增
// 对于每一个给定的起始下标start，通过二分查找的方法可以在O(logn)时间内找到结束下标end
// 因此总时间复杂度是O(nlogn)

class Solution2 {
    public int minSubArrayLen(int s, int[] nums) {
        int[] sums = new int[nums.length + 1]; // sums[i] = sum(nums[0 : i]), i excluded
        for (int i = 1; i < nums.length + 1; i++) {
            sums[i] = sums[i - 1] + nums[i - 1];
        }
        int min = Integer.MAX_VALUE;
        for (int start = 0; start < sums.length; start++) {
            int end = binarySearch(s, sums, start);
            if (end == sums.length) { // no valid end is found
                continue;
            }
            min = Math.min(min, end - start);
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }
    // return the minimum end that satisfies sums[end] - sums[start] >= s
    private int binarySearch(int s, int[] sums, int start) {
        int target = s + sums[start];
        int left = start + 1, right = sums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (sums[mid] >= target) {
                right = mid - 1;
            } else if (sums[mid] < target) {
                left = mid + 1;
            }
        }
        return left;
    }
}
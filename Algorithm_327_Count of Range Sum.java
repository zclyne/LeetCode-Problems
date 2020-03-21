// 与Algorithm_315本质相同，归并排序
// https://leetcode.com/problems/count-of-range-sum/discuss/77990/Share-my-solution

class Solution {
    public int countRangeSum(int[] nums, int lower, int upper) {
        int n = nums.length;
        long[] sums = new long[n + 1]; // must use long here as the sum might overflow
        // notice that sums[i] = sum(nums[0, i)) here (excluding nums[i])
        for (int i = 0; i < n; i++) {
            sums[i + 1] = sums[i] + nums[i];
        }
        return this.countWhileMergeSort(sums, 0, n + 1, lower, upper);
    }
    private int countWhileMergeSort(long[] sums, int start, int end, int lower, int upper) {
        if (end - start <= 1) {
            return 0;
        }
        int mid = start + (end - start) / 2;
        int count = this.countWhileMergeSort(sums, start, mid, lower, upper)
                  + this.countWhileMergeSort(sums, mid, end, lower, upper);
        int j = mid, k = mid, t = mid;
        // cache is used to store the sorted elements in sums[start : end]
        long[] cache = new long[end - start];
        // as the left half has already been handled, we only need to consider
        // elements in the right half that satisfy the restriction for every element
        // sums[i] in the left half
        for (int i = start, posToPutInCache = 0; i < mid; i++) {
            // look for the minimum k so that sums[k] - sums[i] >= lower
            while (k < end && sums[k] - sums[i] < lower) {
                k++;
            }
            // look for the minimum j so that sums[j] - sums[i] > upper
            while (j < end && sums[j] - sums[i] <= upper) {
                j++;
            }
            // add all elements in the right half that is less than sums[i] to cache
            while (t < end && sums[t] < sums[i]) {
                cache[posToPutInCache++] = sums[t++];
            }
            // put sums[i] into cache
            cache[posToPutInCache++] = sums[i];
            // now, j - k is the number of elements that satisfy the restriction
            count += j - k;
        }
        // copy cache back into sums
        // notice that the length must be t- start, not end - start
        // this is because t might be less than end, which happens when several elements
        // at the end of the right half might be larger than every element in the left half
        System.arraycopy(cache, 0, sums, start, t - start);
        return count;
    }
}
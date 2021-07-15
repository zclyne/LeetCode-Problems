// 方法1：排序+贪心
// 先对arr从小到大排序，然后从i = 1开始遍历
// 若发现arr[i] > arr[i - 1]就将arr[i] decrease到arr[i - 1] + 1
// 若arr[i] == arr[i - 1]，保持不变
// 最终arr[arr.length - 1]就是结果

class Solution {
    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        Arrays.sort(arr);
        arr[0] = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > arr[i - 1]) {
                arr[i] = arr[i - 1] + 1;
            }
        }
        return arr[arr.length - 1];
    }
}

// 方法2：计数排序+贪心
// 答案不会超过n，所以只需要对arr中值不超过n的元素进行计数排序，将值超过n的元素视作n

// https://leetcode-cn.com/problems/maximum-element-after-decreasing-and-rearranging/solution/jian-xiao-he-zhong-xin-pai-lie-shu-zu-ho-mzee/
class Solution {
    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        int n = arr.length;
        int[] cnt = new int[n + 1];
        for (int v : arr) {
            ++cnt[Math.min(v, n)];
        }
        int miss = 0;
        for (int i = 1; i <= n; ++i) {
            if (cnt[i] == 0) {
                ++miss;
            } else {
                miss -= Math.min(cnt[i] - 1, miss); // miss 不会小于 0，故至多减去 miss 个元素
            }
        }
        return n - miss;
    }
}
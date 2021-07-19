import java.util.Arrays;

// 方法：滑动窗口
// 先对nums排序，选择一个nums[right]为target，要尽量把nums中在nums[right]左侧的元素都增加到target
// 用left和right记录当前选择的区间
// 每次都先尝试能否把right右移一位：记diff = nums[right + 1] - nums[right]
// 如果要将right右移，则当前选择的区间内所有元素都要再加上diff的值，因为对于原本的right来说，区间内所有元素
// 都可以被递增到nums[right]。记区间的长度len = right - left + 1，则要判断len * diff和k的关系
// 如果k >= len * diff，说明k还有剩余，可以把right右移；
// 如果k < len * diff，说明无法右移right，我们只能右移left，然后k += nums[right] - nums[left]

class Solution {
    public int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);
        int left = 0, right = 0;
        int result = 0;

        while (right < nums.length) {
            result = Math.max(result, right - left + 1);
            if (right == nums.length - 1) {
                break;
            }
            int diff = nums[right + 1] - nums[right];
            int len = right - left + 1;
            if (len * diff <= k) { // can advance right
                k -= len * diff;
                right++;
            } else { // cannot advance right, advance left
                k += nums[right] - nums[left];
                left++;
            }
        }

        return result;
    }
}

// 方法2：前缀和+二分查找
// https://leetcode-cn.com/problems/frequency-of-the-most-frequent-element/solution/gong-shui-san-xie-cong-mei-ju-dao-pai-xu-kxnk/

class Solution {
    int[] nums, sum;
    int n, k;
    public int maxFrequency(int[] _nums, int _k) {
        nums = _nums;
        k = _k;
        n = nums.length;
        Arrays.sort(nums);

        // 构建前缀和数组
        sum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }

        // 对区间长度进行二分查找，左闭右开区间
        int l = 0, r = n;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (check(mid)) {
                l = mid;
            }
            else {
                r = mid - 1;
            }
        }
        return r;
    }
    boolean check(int len) {
        for (int l = 0; l + len - 1 < n; l++) { // l是区间的左端点
            int r = l + len - 1;
            int cur = sum[r + 1] - sum[l]; // 真实的区间和
            int t = nums[r] * len; // 目标的区间和
            if (t - cur <= k) return true;
        }
        return false;
    }
}
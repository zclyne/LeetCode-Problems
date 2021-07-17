import java.util.Arrays;
import java.util.TreeSet;

// 方法：排序+二分
// 对nums1中的元素排序，记排序后的数组为sorted，然后遍历nums2，对于nums2中的每一个元素num2
// 从sorted中找到最接近num2的数，记为mostClose，然后计算num2和mostClose的差值minDiff
// 原本的nums1[i]和nums2[i]的差值记为diff，对于任意一个i，diff和minDiff之差的最大值记为maxDiff
// 记在没有任何替换时，总共的和为sum，则要求的结果就等于sum - maxDiff

class Solution {
    private final int MOD = 1000000007;

    public int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
        int sum = 0;
        int n = nums1.length;
        int maxDiff = 0;
        int[] sorted = nums1.clone();
        Arrays.sort(sorted);
        for (int i = 0; i < n; i++) {
            int diff = Math.abs(nums1[i] - nums2[i]);
            int mostClose = getMostClose(sorted, nums2[i]);
            int minDiff = Math.abs(mostClose - nums2[i]);
            maxDiff = Math.max(maxDiff, diff - minDiff);
        }

        int result = -maxDiff;
        for (int i = 0; i < n; i++) {
            result = (result + Math.abs(nums1[i] - nums2[i])) % MOD;
        }
        return result;
    }

    private int getMostClose(int[] sorted, int n) {
        int left = 0, right = sorted.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (sorted[mid] > n) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        // now, sorted[left] is the minimum element in sorted that is greater than n
        if (left == sorted.length) {
            return sorted[sorted.length - 1];
        } else if (left == 0) {
            return sorted[0];
        } else {
            if (sorted[left] - n > n - sorted[left - 1]) {
                return sorted[left - 1];
            } else {
                return sorted[left];
            }
        }
    }
}
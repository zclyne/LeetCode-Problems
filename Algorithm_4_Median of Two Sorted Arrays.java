// 思路：https://leetcode.com/problems/median-of-two-sorted-arrays/discuss/2481/Share-my-O(log(min(mn))-solution-with-explanation

class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) { // make sure that nums2.length >= nums1.length
            int[] tmp = nums1;
            nums1 = nums2;
            nums2 = tmp;
        }
        int m = nums1.length, n = nums2.length;
        int imin = 0, imax = m, maxOfLeft, minOfRight;
        double result = 0;
        while (imin <= imax) {
            int i = (imin + imax) / 2, j = (m + n + 1) / 2 - i;
            if (j > 0 && i < m && nums2[j - 1] > nums1[i]) { // increase i
                imin = i + 1;
            } else if (i > 0 && j < n && nums1[i - 1] > nums2[j]) { // decrease i
                imax = i - 1;
            } else { // get to the right place
                if (j == 0) {
                    maxOfLeft = nums1[i - 1];
                } else if (i == 0) {
                    maxOfLeft = nums2[j - 1];
                } else {
                    maxOfLeft = Math.max(nums1[i - 1], nums2[j - 1]);
                }
                if ((m + n) % 2 == 1) { // m + n is odd
                    result = maxOfLeft;
                } else { // m + n is odd
                    if (j == n) {
                        minOfRight = nums1[i];
                    } else if (i == m) {
                        minOfRight = nums2[j];
                    } else {
                        minOfRight = Math.min(nums1[i], nums2[j]);
                    }
                    result = (maxOfLeft + minOfRight) / 2.0;
                }
                break;
            }
        }
        return result;
    }
}
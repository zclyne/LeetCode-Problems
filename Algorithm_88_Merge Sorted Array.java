// 思路：从后向前比较nums1和nums2当前元素的大小，把较大的放到nums1[insertIdx]位置上

class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (nums1 == null || nums2 == null) {
            return;
        }
        int insertIdx = m + n - 1, pos_1 = m - 1, pos_2 = n - 1;
        while (pos_1 >= 0 || pos_2 >= 0) {
            int num1 = pos_1 >= 0 ? nums1[pos_1] : Integer.MIN_VALUE;
            int num2 = pos_2 >= 0 ? nums2[pos_2] : Integer.MIN_VALUE;
            if (num1 > num2) {
                nums1[insertIdx--] = num1;
                pos_1--;
            } else {
                nums1[insertIdx--] = num2;
                pos_2--;
            }
        }
    }
}
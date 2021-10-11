class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1, p2 = n - 1;
        int cur = m + n - 1;
        while (p1 >= 0 || p2 >= 0) {
            int n1 = p1 >= 0 ? nums1[p1] : Integer.MIN_VALUE;
            int n2 = p2 >= 0 ? nums2[p2] : Integer.MIN_VALUE;
            if (n1 < n2) {
                nums1[cur] = n2;
                p2--;
            } else {
                nums1[cur] = n1;
                p1--;
            }
            cur--;
        }
    }
}
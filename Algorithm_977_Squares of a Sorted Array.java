// 双指针

class Solution {
    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        int left = 0, right = n - 1;
        int i = n - 1;
        while (left <= right) {
            int nl = nums[left], nr = nums[right];
            if (nl * nl < nr * nr) {
                result[i] = nr * nr;
                right--;
            } else {
                result[i] = nl * nl;
                left++;
            }
            i--;
        }
        return result;
    }
}
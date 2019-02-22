// 思路：bit manipulation。其他数都出现2次，因此异或后得到0。把nums中的所有数做异或，结果就是只出现一次的那个数

class Solution {
    public int singleNumber(int[] nums) {
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            res ^= nums[i];
        }
        return res;
    }
}
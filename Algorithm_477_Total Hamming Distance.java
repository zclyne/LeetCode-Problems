// 思路：bitCount[i]存储第i个比特为1的nums中的数的个数
// 如果nums中共有n个数，其中有k个数在第i个比特为1，则有n - k个数在第i个比特为0
// 第i个比特对于结果result的贡献为k * (n - k)

class Solution {
    public int totalHammingDistance(int[] nums) {
        int result = 0;
        int[] bitCount = new int[32];
        for (int i = 0; i < 32; i++) {
            for (int j = 0; j < nums.length; j++) {
                bitCount[i] += (nums[j] >> i) & 1;
            }
            result += bitCount[i] * (nums.length - bitCount[i]);
        }
        return result;
    }
}
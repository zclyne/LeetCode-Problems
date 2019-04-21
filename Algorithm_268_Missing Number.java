// Bit Manipulation Solution
// 思路：由于nums中的元素在范围0 ~ n中，因此若某个元素在nums中出现过，它与res异或一次，又由于下标异或一次，两次相抵消
// 而missing number只被异或了一次，在遍历结束后存储在最终的res中
// 由于下标最大到nums.length - 1，为确保n本身也参加两次异或，要把res的初始值置为n，也就是nums.length

class Solution {
    public int missingNumber(int[] nums) {
        int res = nums.length, i = 0;
        for (; i < nums.length; i++) {
            res ^= i ^ nums[i];
        }
        return res;
    }
}

// Sum Solution
// 思路：把0 ~ n所有数字求和，再依次减去nums中的各个元素，剩余的那个就是未出现的元素

class SumSolution {
    public int missingNumber(int[] nums) { //sum
        int len = nums.length;
        int sum = (0 + len) * (len + 1) / 2;
        for(int i = 0; i < len; i++)
            sum -= nums[i];
        return sum;
    }
}
// 思路：先从左到右遍历nums，把nums[i]左侧的所有数的乘积计算出来并赋予result[i]
// 再从右到左遍历nums，把nums[i]右侧的所有数的乘积计算出来并乘到result[i]上，注意此处不是赋值给result[i]

class Solution {
    public int[] productExceptSelf(int[] nums) {
        int leftProduct = 1, rightProduct = 1;
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            result[i] = leftProduct;
            leftProduct *= nums[i];
        }
        for (int i = nums.length - 1; i >= 0; i--) {
            result[i] *= rightProduct;
            rightProduct *= nums[i];
        }
        return result;
    }
}
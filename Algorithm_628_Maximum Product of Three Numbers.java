// 思路：一共有以下四种情况和每种情况分别对应的乘积最大的3个数的组合：
// 3 positive -> 3 largest (+)
// 2 positive + 1 negative -> 1 largest (+) 2 smallest (-)
// 1 positive + 2 negative -> 1 largest (+) 2 smallest (-)
// 3 negative -> 3 largest (-)

class Solution {
    public int maximumProduct(int[] nums) {
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE, min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num > max1) {
                max3 = max2;
                max2 = max1;
                max1 = num;
            } else if (num > max2) {
                max3 = max2;
                max2 = num;
            } else if (num > max3) {
                max3 = num;
            }
            if (num < min1) {
                min2 = min1;
                min1 = num;
            } else if (num < min2) {
                min2 = num;
            }
        }
        return Math.max(max1 * max2 * max3, max1 * min1 * min2);
    }
}
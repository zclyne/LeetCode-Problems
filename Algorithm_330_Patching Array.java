// 方法：观察到数学规律：对于正整数x，如果区间[1, x - 1]中的所有数字都已经能够被nums中的若干数之和覆盖到
// 则将x加入nums中后，[1, 2x - 1]区间内的所有数字也都可以被覆盖
// 假设数字x缺失，则至少需要在数组nums中添加一个小于等于x的数，才能覆盖到x
// 从贪心的角度，如果[1, x - 1]中的数字都已经能够被覆盖，则补充x后，即可覆盖到x，并且补充的数字个数最少
// 在补充x后，[1, 2x - 1]范围内的所有数字都能够被覆盖，下一个缺失的数字一定大于等于2x
// 由此，可以提出这样一个贪心的方案：
// 每次找到未被数组nums覆盖的最小的整数x，在数组中补充x，然后寻找下一个未被覆盖的最小的整数，重复上述步骤直到区间[1,n]中的所有数字都被覆盖
// https://leetcode-cn.com/problems/patching-array/solution/an-yao-qiu-bu-qi-shu-zu-by-leetcode-solu-klp1/

class Solution {
    public int minPatches(int[] nums, int n) {
        int index = 0, result = 0;
        long x = 1;
        while (x <= n) {
            if (index < nums.length && nums[index] <= x) { // nums[index]已经包含在了可以覆盖的范围内，或正好等于x
                // 由于之前遍历过的nums下标范围是0 ~ index - 1，所以相当于nums[index]还没有被加入nums
                // 现在将它加入nums，但是result不需要++
                // 而能够覆盖的范围由原本的[1, x - 1]扩大到了[1, x + nums[index] - 1]
                x += nums[index];
                index++;
            } else { // 需要把x加入到nums中
                // 能够覆盖的范围由原本的[1, x - 1]扩大到了[1, 2x - 1]
                x *= 2;
                result++;
            }
        }
        return result;
    }
}
// 思路：首先判断数组长度是否小于2，若小于，则直接返回false
// 然后遍历一遍原数组，看是否存在2个连续的0。若存在这种情况，那么无论k为何值都可以直接返回true
// 然后判断k是否为0。若k为0，又由于数组中不存在连续的2个0，因此可以直接返回false
// 若以上情况都不满足，则构建HashMap，该map的键为从nums[0]~nums[i]所有数之和模k的值，值为i
// 注意这里键不能直接用sum。因为如果键为sum，遍历时就需要内部再套一层循环
// 对nums从0开始遍历，对nums[0]~nums[i]求和的值记为sum，若sum本身时k的倍数，直接返回true
// 若sum不是k的倍数，但是先前存在某一subarray（nums[0]~nums[j]）的所有数之和模k等于sum模k，并且j < i - 1
// 那么nums[j + 1]~nums[i]就是满足要求的subarray，返回true
// 否则，将sum % k和i分别作为键和值加入map中

import java.util.HashMap;

class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        if (nums.length < 2) return false;
        for (int i = 0; i < nums.length - 1; i++)
            if (nums[i] == 0 && nums[i + 1] == 0)
                return true;
        if (k == 0) return false;
        if (k < 0) k = -k;
        HashMap<Integer, Integer> map = new HashMap<>();
        int sum = nums[0];
        map.put(sum, 0);
        for (int i = 1; i < nums.length; i++) {
            sum += nums[i];
            if (sum % k == 0 || map.getOrDefault(sum % k, i) < i - 1)
                return true;
            else
                map.put(sum % k, i);
        }
        return false;
    }
}
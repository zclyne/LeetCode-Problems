import java.util.HashMap;

// 思路：用curSum记录下从0开始,以i结尾的subarray中所有元素之和， 并以该和为键存入HashMap，值为该和出现的次数
// 开始遍历前，先在map中存入(0, 1)，这是为了处理[0, i]的subarray所有数之和恰好为k的情况，此时count要加1
// target = curSum - k，以此值去HashMap中查找起始下标为0、结尾下标小于i且所有元素之和为target的subarray的个数
// 该个数即为结尾下标为i且所有元素值和为k的subarray的个数。将该个数加到count上
// 最后，判断新的curSum是否存在于map中，若不存在则插入(curSum, 1)；若存在，则插入(curSum, map.get(curSum) + 1)
// 表示出现了一个从0开始且所有元素之和为curSum的subarray

class Solution {
    public int subarraySum(int[] nums, int k) {
        int count = 0, curSum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            curSum += nums[i];
            count += map.getOrDefault(curSum - k, 0);
            map.put(curSum, map.getOrDefault(curSum, 0) + 1);
        }
        return count;
    }
}
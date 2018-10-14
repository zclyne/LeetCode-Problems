import java.util.HashMap;

// 思路：用tmpSum记录下从0开始,以i结尾的subarray中所有元素之和， 并以该和为键存入HashMap，值为该和出现的次数
// 在对数组nums遍历时，若tmpSum正好等于k，则表明从[0, i]的subarray满足条件，totalSum++
// 再令target = tmpSum - k，以此值去HashMap中查找起始下标为0、结尾下标小于i且所有元素之和为target的subarray的个数
// 该个数即为结尾下标为i且所有元素值和为k的subarray的个数。将该个数加到totalSum上
class Solution {
    public int subarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int tmpSum = 0, totalSum = 0;
        for (int i = 0; i < nums.length; i++)
        {
            tmpSum += nums[i];
            int target = tmpSum - k;
            if (target == 0) totalSum++; // 以0开始、i结尾的subarray各元素之和正好为k
            Integer tmpCount = map.get(target);
            if (tmpCount != null) totalSum += tmpCount;
            if (!map.containsKey(tmpSum)) map.put(tmpSum, 1);
            else map.put(tmpSum, map.get(tmpSum) + 1);
        }
        return totalSum;
    }
}
import java.util.HashMap;
import java.util.Map;

// 方法1：前缀和+hashmap
// 变量sum存储nums中的前缀和
// map中保存实时的各前缀和的出现次数
// 遍历nums，设当前下标为i
// 则map中保存的是nums[0, j] , 0 <= j < i这些前缀的前缀和的出现次数
// sum = sum(nums[0] ~ nums[i])
// 现在i固定，要找下标j，使得sum(nums[j] ~ nums[i]) == goal，就等价于在map中找sum - goal的出现次数
// 出现次数为几次，就说明有几个j满足条件sum(nums[j] ~ nums[i]) == goal

class Solution {
    public int numSubarraysWithSum(int[] nums, int goal) {
        int result = 0;
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            map.put(sum, map.getOrDefault(sum, 0) + 1);
            sum += nums[i];
            result += map.getOrDefault(sum - goal, 0);
        }

        return result;
    }
}



// 方法2：滑动窗口
// 当j固定时，对于每个下标i, i <= j，使得子数组nums[i] ~ nums[j]满足条件的i总是落在一段连续的区间内
// 并且随着j的右移，这个区间的左右端点也将右移
// 令滑动窗口的右边界为right，左边界left1、left2表示区间[left1, left2)，此时有left2 - left1个区间都满足条件
// 找left2的过程就等价于找到最小的left2，使得sum(nums[left2] ~ nums[right]) ? goal

class Solution {
    public int numSubarraysWithSum(int[] nums, int goal) {
        int result = 0;
        int sum1 = 0, sum2 = 0, left1 = 0, left2 = 0, right = 0;

        while (right < nums.length) {
            sum1 += nums[right];
            sum2 += nums[right];
            while (left1 <= right && sum1 > goal) { // 找到最小的left1，使得sum(nums[left1] ~ nums[right]) == goal
                sum1 -= nums[left1];
                left1++;
            }

            while (left2 <= right && sum2 >= goal) { // 找到最小的left2，使得sums(nums[left2] ~ nums[right]) > goal
                sum2 -= nums[left2];
                left2++;
            }
            result += left2 - left1;
            right++;
        }

        return result;
    }
}
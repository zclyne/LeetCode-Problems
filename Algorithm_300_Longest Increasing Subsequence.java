import java.util.Arrays;

// Binary Search Solution O(nlogn)
// 思路：tails[i]存储长度为i - 1的所有递增subsequence最后一个元素中的最小者
// 易知，tails递增
// 遍历nums，对于当前元素num，可以通过Binary Search找到它在tails中应该在的位置
// 1. 若num大于当前tails中的所有元素，则以num结尾形成了更长的递增subsequence，则把num添加到tails结尾，并size++
// 2. 若num <= tails[0]或tails[i - 1] <= num < tails[i]，则要把tails[0]或tails[i]更新为num
// 最后tails的size就是最长的递增subsequence的长度

class Solution {
    public int lengthOfLIS(int[] nums) {
        int[] tails = new int[nums.length];
        int size = 0;
        for (int num : nums) {
            int left = 0, right = size;
            while (left < right) {
                int mid = (left + right) / 2;
                if (tails[mid] < num) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            tails[left] = num;
            if (left == size) { // num is greater than any number in tails
                size++;
            }
        }
        return size;
    }
}


// My DP Solution O(nlogn)

class Solution2 {
    public int lengthOfLIS(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int res = 1;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], 1 + dp[j]);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
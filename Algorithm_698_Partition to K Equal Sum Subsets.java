// 思路：visited[i]存放的值表示nums[i]是否已经在某个subset中被使用
// 显然，k = 1时一定可以partition，直接返回true
// curSum追踪的是当前subset中所有元素之和
// 要注意当nums中所有元素之和恰好等于0时是边界条件，因此要用curNum来追踪当前subset中的数字个数，只有在curNum > 0时这才是一个可用的subset
// 当curSum等于targetSum且curNum > 0时，当前所选的subset满足条件，从剩下尚未visit的所有num中再去找满足条件的subset，并且k = k - 1

class Solution {
    private boolean[] visited;
    public boolean canPartitionKSubsets(int[] nums, int k) {
        if (k == 1)
            return true;
        int sum = 0;
        for (int num : nums)
            sum += num;
        if (sum % k != 0)
            return false;
        visited = new boolean[nums.length];
        return canPartition(nums, k, 0, 0, 0, sum / k);
    }
    public boolean canPartition(int[] nums, int k, int startIdx, int curNum, int curSum, int targetSum) {
        if (k == 1)
            return true;
        if (curSum == targetSum && curNum > 0)
            return canPartition(nums, k - 1, 0, 0, 0, targetSum);
        for (int i = startIdx; i < nums.length; i++) {
            if (!visited[i] && nums[i] + curSum <= targetSum) {
                visited[i] = true;
                if (canPartition(nums, k, i, curNum + 1, curSum + nums[i], targetSum))
                    return true;
                visited[i] = false;
            }
        }
        return false;
    }
}
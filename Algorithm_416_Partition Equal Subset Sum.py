class Solution:
    def canPartition(self, nums: List[int]) -> bool:
        if not nums or len(nums) == 1:
            return False
        halfSum = sum(nums)
        if halfSum & 1 != 0: # sum is odd, cannot partition
            return False
        halfSum = halfSum // 2
        dp = [False for _ in range(halfSum + 1)]
        dp[0] = True
        for num in nums:
            for i in range(halfSum, num - 1, -1):
                dp[i] |= dp[i - num]
        return dp[halfSum]
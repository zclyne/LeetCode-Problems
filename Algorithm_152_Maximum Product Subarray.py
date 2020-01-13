class Solution:
    def maxProduct(self, nums: List[int]) -> int:
        res = curMax = curMin = nums[0]
        for i in range(1, len(nums)):
            newMax = newMin = 0
            if nums[i] >= 0:
                newMax = max(nums[i], curMax * nums[i])
                newMin = min(nums[i], curMin * nums[i])
            else:
                newMax = max(nums[i], curMin * nums[i])
                newMin = min(nums[i], curMax * nums[i])
            curMax, curMin = newMax, newMin
            res = max(res, curMax)
        return res
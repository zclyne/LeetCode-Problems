class Solution:
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        dic = {nums[0]: 0}
        for i in range(1, len(nums)):
            if target - nums[i] in dic:
                return [dic[target-nums[i]], i]
            else:
                dic[nums[i]] = i
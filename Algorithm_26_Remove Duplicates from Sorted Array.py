class Solution:
    def removeDuplicates(self, nums: List[int]) -> int:
        if not nums or len(nums) <= 1:
            return len(nums)
        newLen = 1
        for i in range(1, len(nums)):
            if nums[i] != nums[i - 1]:
                nums[newLen] = nums[i]
                newLen += 1
        return newLen
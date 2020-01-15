class Solution:
    def moveZeroes(self, nums: List[int]) -> None:
        lastNotZeroIdx = 0
        for i in range(len(nums)):
            if nums[i] != 0:
                nums[lastNotZeroIdx] = nums[i]
                lastNotZeroIdx += 1
        for i in range(lastNotZeroIdx, len(nums)):
            nums[i] = 0
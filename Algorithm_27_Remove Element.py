class Solution:
    def removeElement(self, nums: List[int], val: int) -> int:
        if not nums:
            return 0
        newLen = 0
        for i in range(len(nums)):
            if nums[i] != val:
                nums[newLen] = nums[i]
                newLen += 1
        return newLen
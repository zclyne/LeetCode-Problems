class Solution:
    def majorityElement(self, nums: List[int]) -> int:
        major, count = nums[0], 1
        for i in range(1, len(nums)):
            if count == 0:
                major, count = nums[i], 1
            else:
                count = count + 1 if nums[i] == major else count - 1
        return major
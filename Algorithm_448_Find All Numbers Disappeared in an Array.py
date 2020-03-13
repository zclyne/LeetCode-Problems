class Solution:
    def findDisappearedNumbers(self, nums: List[int]) -> List[int]:
        result = []
        for i in range(len(nums)):
            idx = abs(nums[i])
            if nums[idx - 1] >  0:
                nums[idx - 1] = -nums[idx - 1]
        for i in range(len(nums)):
            if nums[i] > 0: # i + 1 never doesn't exist in nums
                result.append(i + 1)
        return result
class Solution:
    def findDuplicates(self, nums: List[int]) -> List[int]:
        result = []
        for i in range(len(nums)):
            idx = abs(nums[i])
            if nums[idx - 1] < 0: # idx is duplicate
                result.append(idx)
            else:
                nums[idx - 1] = -nums[idx - 1]
        return result
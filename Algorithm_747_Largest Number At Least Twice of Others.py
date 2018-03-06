class Solution:
    def dominantIndex(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        max_index=0
        for i in range(1,len(nums)):
            if nums[i]>nums[max_index]:
                max_index=i
        for i in range(len(nums)):
            if i==max_index: continue
            if nums[max_index]<2*nums[i]: return -1
        return max_index
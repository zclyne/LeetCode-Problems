class Solution:
    def pivotIndex(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        if len(nums)==0: return -1
        elif len(nums)==1: return 0
        leftSum,rightSum=0,sum(nums[1:])
        if rightSum==0: return 0
        for piv in range(1,len(nums)):
            leftSum+=nums[piv-1]
            rightSum-=nums[piv]
            if leftSum==rightSum: return piv
        return -1
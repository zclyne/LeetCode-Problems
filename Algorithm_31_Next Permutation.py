class Solution:
    def nextPermutation(self, nums):
        last=-1
        for i in range(len(nums)-1):
            if nums[i]<nums[i+1]:
                last=i
        if last==-1:#the last permutation
            for i in range(len(nums)//2): nums[i],nums[len(nums)-i-1]=nums[len(nums)-i-1],nums[i]
        elif last==len(nums)-2:
            nums[last],nums[last+1]=nums[last+1],nums[last]
        else:
            lastmax=-1
            for i in range(last+1,len(nums)):
                if nums[i]>nums[last]: lastmax=i
            nums[last],nums[lastmax]=nums[lastmax],nums[last]
            nums[last+1:]=sorted(nums[last+1:])
        return
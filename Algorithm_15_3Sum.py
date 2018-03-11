class Solution:
    def threeSum(self, nums):
        res=[]
        nums.sort()
        for i in range(len(nums)-2):
            if i>0 and nums[i]==nums[i-1]: continue
            low=i+1
            high=len(nums)-1
            while low<high:
                if low<high and nums[i]+nums[low]+nums[high]==0:
                    res.append([nums[i],nums[low],nums[high]])
                    while low<high and nums[low]==nums[low+1]: low+=1
                    while low<high and nums[high]==nums[high-1]: high-=1
                    low+=1
                    high-=1
                elif nums[i]+nums[low]+nums[high]<0: low+=1
                else: high-=1
        return res
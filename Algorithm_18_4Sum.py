class Solution:
    def fourSum(self, nums, target):
        tmp,res=[],[]
        nums.sort()
        for i in range(len(nums)-3):
            if i>0 and nums[i]==nums[i-1]: continue
            if nums[i]+nums[i+1]+nums[i+2]+nums[i+3]>target: break
            if nums[i]+nums[len(nums)-1]+nums[len(nums)-2]+nums[len(nums)-3]<target: continue
            for j in range(i+1,len(nums)-2):
                if j>i+1 and nums[j]==nums[j-1]:continue
                if nums[j]+nums[j+1]+nums[j+2]>target-nums[i]: break
                if nums[j]+nums[len(nums)-1]+nums[len(nums)-2]<target-nums[i]: continue
                low,high=j+1,len(nums)-1
                while low<high:
                    if low<high and nums[i]+nums[j]+nums[low]+nums[high]==target:
                        tmp.append([nums[i],nums[j],nums[low],nums[high]])
                        while low<high and nums[low]==nums[low+1]: low+=1
                        while low<high and nums[high]==nums[high-1]: high-=1
                        low+=1
                        high-=1
                    elif nums[i]+nums[j]+nums[low]+nums[high]<target: low+=1
                    else: high-=1
        for i in range(len(tmp)):
            if res.count(tmp[i])==0: res.append(tmp[i])
        return res
class Solution:
    def threeSumClosest(self, nums, target):
        nums.sort()
        res=nums[0]+nums[1]+nums[len(nums)-1]
        dif=abs(target-res)
        for i in range(len(nums)-2):
            if i>0 and nums[i]==nums[i-1]: continue
            low,high=i+1,len(nums)-1
            while low<high:
                sum=nums[i]+nums[low]+nums[high]
                if sum==target: return sum
                tmpDif=abs(target-sum)
                if tmpDif<dif:
                    res=sum
                    dif=tmpDif
                if sum<target: low+=1
                else: high-=1
        return res
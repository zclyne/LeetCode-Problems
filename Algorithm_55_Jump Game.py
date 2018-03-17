class Solution:
    def canJump(self, nums):
        if len(nums)==1:
            return True
        if nums[0]==0:
            return False
        lastVisited=0
        for i in range(len(nums)):
            if nums[i]+i<lastVisited:
                continue
            if i==lastVisited and nums[i]==0:
                return False
            lastVisited=max(lastVisited,i+nums[i])
            if lastVisited>=len(nums)-1:
                return True
        return False
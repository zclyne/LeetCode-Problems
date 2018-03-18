class Solution:
    def divideAndSearch(self,nums,target,low,high):
        if low>high:
            return False
        elif low==high:
            return nums[low]==target
        else:
            mid=(high+low)//2
            if nums[mid]==target:
                return True
            elif nums[mid]>target:
                return self.divideAndSearch(nums,target,low,mid-1)
            else:
                return self.divideAndSearch(nums,target,mid+1,high)

    def search(self, nums, target):
        if len(nums)==0:
            return False
        if len(nums)==1:
            return nums[0]==target
        pivot=-1
        for i in range(len(nums)-1):
            if nums[i]>nums[i+1]:
                pivot=i
                break
        if pivot==-1:
            return self.divideAndSearch(nums,target,0,len(nums)-1)
        leftMin,rightMax=nums[0],nums[len(nums)-1]
        if target>=leftMin:
            return self.divideAndSearch(nums,target,0,pivot)
        elif target<=rightMax:
            return self.divideAndSearch(nums,target,pivot+1,len(nums)-1)
        else:
            return False
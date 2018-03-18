class Solution:
    def get(self, nums, tmpRes, res, cur):
        if cur==len(nums):
            res.append(tmpRes[:])
            return
        tmpRes.append(nums[cur])
        Solution.get(self,nums,tmpRes,res,cur+1)
        tmpRes.pop()
        Solution.get(self,nums,tmpRes,res,cur+1)

    def subsets(self, nums):
        n=len(nums)
        if n==0: return [[]]
        res=[]
        Solution.get(self,nums,[],res,0)
        return res
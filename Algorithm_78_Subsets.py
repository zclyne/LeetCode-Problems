class Solution:
    def get(self, nums, bits, res, cur):
        if cur==len(bits):
            tmpRes=[]
            for i in range(len(bits)):
                if bits[i]==1:
                    tmpRes.append(nums[i])
            res.append(tmpRes)
            return
        bits[cur]=1
        Solution.get(self,nums,bits,res,cur+1)
        bits[cur]=0
        Solution.get(self,nums,bits,res,cur+1)

    def subsets(self, nums):
        n=len(nums)
        if n==0: return [[]]
        res=[]
        bits=[0 for i in range(n)]
        Solution.get(self,nums,bits,res,0)
        return res
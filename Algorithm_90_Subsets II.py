# class Solution:
#     def getCombinations(self, n, tmpRes, coms):
#         if n==0:
#             coms.append(tmpRes)
#             return
#         Solution.getCombinations(self, n-1, tmpRes+[0], coms)
#         Solution.getCombinations(self, n-1, tmpRes+[1], coms)
#     def subsetsWithDup(self, nums):
#         n=len(nums)
#         coms,res=[],[]
#         if n==0: return []
#         Solution.getCombinations(self, n, [], coms)
#         for com in coms:
#             tmpRes=[]
#             for i in range(len(com)):
#                 if com[i]==1: tmpRes.append(nums[i])
#             tmpRes.sort()
#             if tmpRes not in res: res.append(tmpRes)
#         return res

class Solution:
    # @param num, a list of integer
    # @return a list of lists of integer
    def subsetsWithDup(self, S):
        res = [[]]
        S.sort()
        for i in range(len(S)):
            if i == 0 or S[i] != S[i - 1]:
                l = len(res)
            for j in range(len(res) - l, len(res)):
                res.append(res[j] + [S[i]])
        return res
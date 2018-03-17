class Solution:
    def comb(self, select, k, cur, res):
        if cur==len(select):
            if k!=0: return
            thisRes=[]
            for i in range(len(select)):
                if select[i]:
                    thisRes.append(i+1)
            res.append(thisRes[:])
            return
        select[cur]=True
        Solution.comb(self,select,k-1,cur+1,res)
        select[cur]=False
        Solution.comb(self,select,k,cur+1,res)
    def combine(self, n, k):
        """
        :type n: int
        :type k: int
        :rtype: List[List[int]]
        """
        select=[False for i in range(n)]
        res=[]
        Solution.comb(self,select,k,0,res)
        return res

sol=Solution()
res=sol.combine(4,2)
print (res)
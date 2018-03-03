class Solution:
    def getDigits(self, num):
        res=[]
        while num!=0:
            cur=num%10
            num=num//10
            res.append(cur)
        return res
    def selfDividingNumbers(self, left, right):
        """
        :type left: int
        :type right: int
        :rtype: List[int]
        """
        res=[]
        for i in range(left,right+1):
            digit=Solution.getDigits(self,i)
            flag=True
            for j in digit:
                if j==0 or i%j!=0:
                    flag=False
                    break
            if flag:
                res.append(i)
        return res
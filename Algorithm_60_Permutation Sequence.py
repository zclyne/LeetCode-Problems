class Solution:
    def getPermutation(self, n, k):
        allNums=[i for i in range(1,n+1)]
        fac=1
        for i in range(2,n+1):
            fac*=i
        string=''
        for cur in range(n):
            fac=fac//(n-cur)
            thisBit=allNums[(k-1)//fac]
            string+=chr(ord('0')+thisBit)
            allNums.remove(thisBit)
            k-=((k-1)//fac)*fac
            if k==0:
                tmpStr=''
                for i in allNums:
                    tmpStr+=chr(ord('0')+i)
                string+=tmpStr
                return string
        return string
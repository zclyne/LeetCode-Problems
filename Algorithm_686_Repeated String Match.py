class Solution:
    def repeatedStringMatch(self, A, B):
        """
        :type A: str
        :type B: str
        :rtype: int
        """
        tmp=A*(len(B)//len(A))
        for i in range(len(B)//len(A),len(B)//len(A)+3):
            if B in tmp:
                return i
            tmp+=A
        return -1
class Solution:
    def numJewelsInStones(self, J, S):
        """
        :type J: str
        :type S: str
        :rtype: int
        """
        dic={}
        count=0
        for ch in J: dic[ch]=1
        for ch in S:
            if ch in dic: count+=1
        return count
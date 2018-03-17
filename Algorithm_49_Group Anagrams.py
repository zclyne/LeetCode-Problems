class Solution:
    def groupAnagrams(self, strs):
        nums=[2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97,101]
        dic={}
        for string in strs:
            product=1
            for ch in string:
                product*=nums[ord(ch)-ord('a')]
            if product not in dic:
                dic[product]=[]
            dic[product].append(string)
        res=[]
        for key in dic:
            res.append(dic[key])
        return res
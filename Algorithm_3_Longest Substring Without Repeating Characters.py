class Solution:
    def lengthOfLongestSubstring(self, s):
        start=curLen=maxLen=0
        map={}
        for i in range(len(s)):
            if map.get(s[i],-1)==-1:
                curLen+=1
                if curLen>maxLen:
                    maxLen=curLen
            else:
                for j in range(start,map[s[i]]):
                    map[s[j]]=-1
                start=map[s[i]]+1
                curLen=i-start+1
            map[s[i]]=i
        return maxLen
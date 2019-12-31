class Solution:
    def longestPalindrome(self, s: str) -> str:
        if not s:
            return ""
        dp = [[0 for i in range(len(s))] for i in range(len(s))]
        maxLen = maxStart = maxEnd = 0
        for length in range(len(s)):
            for curStart in range(len(s) - length):
                if length <= 2:
                    dp[curStart][curStart + length] = 1 if s[curStart] == s[curStart + length] else 0
                else:
                    dp[curStart][curStart + length] = 1 if s[curStart] == s[curStart + length] and dp[curStart + 1][curStart + length - 1] else 0
                if dp[curStart][curStart + length] and length + 1 > maxLen:
                    maxLen = length + 1
                    maxStart = curStart
                    maxEnd = curStart + length
        return s[maxStart:maxEnd + 1]

    def longestPalindrome1(self, s:str) -> str:
        if not s:
            return ""
        res = ""
        for i in range(len(s)):
            tmp = self.helper(s, i, i)
            if len(tmp) > len(res):
                res = tmp
            tmp = self.helper(s, i, i + 1)
            if len(tmp) > len(res):
                res = tmp
        return res

    def helper(self, s:str, left:int, right:int) -> str:
        while left >= 0 and right < len(s) and s[left] == s[right]:
            left -= 1
            right += 1
        return s[left + 1:right]
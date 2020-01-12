class Solution:
    def wordBreak(self, s: str, wordDict: List[str]) -> bool:
        # dp[i] stores weather s[0:i] can be segmented by words in wordDict
        dp = [False for _ in range(len(s) + 1)]
        dp[0] = True
        for start in range(len(s)):
            if dp[start]: # s[0:start] can be segmented
                for word in wordDict:
                    if start + len(word) > len(s): # word is too long
                        continue
                    if s[start:start + len(word)] == word:
                        dp[start + len(word)] = True
        return dp[len(s)]
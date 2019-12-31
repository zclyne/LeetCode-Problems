class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        if not s:
            return 0
        lastSeenIndicies = {}
        maxLen = start = 0
        for i in range(len(s)):
            c = s[i]
            lastSeenIndex = lastSeenIndicies[c] if c in lastSeenIndicies else -1
            if start <= lastSeenIndex:
                start = lastSeenIndex + 1
            maxLen = max(maxLen, i - start + 1)
            lastSeenIndicies[c] = i
        return maxLen
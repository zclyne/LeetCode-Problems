class Solution:
    def rob(self, nums: List[int]) -> int:
        lastRob = lastNotRob = 0
        for num in nums:
            newRob = lastNotRob + num
            newNotRob = max(lastRob, lastNotRob)
            lastRob, lastNotRob = newRob, newNotRob
        return max(lastRob, lastNotRob)
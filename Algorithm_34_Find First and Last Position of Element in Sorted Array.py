# 思路：https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/discuss/14699/Clean-iterative-solution-with-two-binary-searches-(with-explanation)

class Solution:
    def searchRange(self, nums: List[int], target: int) -> List[int]:
        if not nums:
            return [-1, -1]
        left = 0
        right = len(nums) - 1
        res = [-1, -1]
        # look for start
        while left < right: # notice < not <=
            mid = (left + right) // 2 # mid biased to left
            if nums[mid] < target:
                left = mid + 1 # left = mid + 1 ensures that the program would not stuck at left == mid
            else:
                right = mid
        if nums[left] != target: # fail to find target
            return res
        res[0] = left
        # look for end, no need to reset left to 0
        right = len(nums) - 1
        while left < right:
            mid = (left + right) // 2 + 1 # make mid biased to right
            if nums[mid] > target:
                right = mid - 1 # right = mid - 1 ensures that the program would not stuck at right == mid
            else:
                left = mid
        res[1] = left
        return res
class Solution:
    def lengthOfLIS(self, nums: List[int]) -> int:
        tails = [0 for _ in range(len(nums))]
        size = 0
        for num in nums:
            left, right = 0, size
            while left < right:
                mid = (left + right) // 2
                if tails[mid] < num:
                    left = mid + 1
                else:
                    right = mid
            tails[left] = num
            if left == size:
                size += 1
        return size
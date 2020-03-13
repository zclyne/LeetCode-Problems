class Solution:
    def rotate(self, nums: List[int], k: int) -> None:
        if len(nums) <= 1 or k % len(nums) == 0:
            return
        k = k % len(nums)
        self.reverse(nums, 0, len(nums) - 1)
        self.reverse(nums, 0, k - 1)
        self.reverse(nums, k, len(nums) - 1)
    
    def reverse(self, nums, start, end) -> None:
        for i in range(0, (end - start + 1) // 2):
            nums[start + i], nums[end - i] = nums[end - i], nums[start + i]

# 同样的方法，但写法更简便
class SolutionOptimized:
    def rotate(self, nums: List[int], k: int) -> None:
        n = len(nums)
        k = k % n
        nums[:] = nums[n-k:] + nums[:n-k] # notice nums[:] here, [:] is indispensable
class Solution:
    def productExceptSelf(self, nums: List[int]) -> List[int]:
        res = [1 for _ in range(len(nums))]
        leftProduct = rightProduct = 1
        for i in range(len(nums)):
            res[i] *= leftProduct
            leftProduct *= nums[i]
        for i in range(len(nums) - 1, -1, -1):
            res[i] *= rightProduct
            rightProduct *= nums[i]
        return res
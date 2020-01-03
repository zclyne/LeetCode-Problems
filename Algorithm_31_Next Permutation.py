class Solution:
    def nextPermutation(self, nums: List[int]) -> None:
        if not nums or len(nums) < 2:
            return
        # look for the largest k so that nums[k] < nums[k + 1]
        k = len(nums) - 2
        while k >= 0 and nums[k] >= nums[k + 1]:
            k -= 1
        # if nums is the last permutation, reverse nums and return
        if k == -1:
            nums[:] = nums[::-1]
            return
        # look for the largest l so that l > k and nums[l] > nums[k]
        l = len(nums) - 1
        while l > k and nums[l] <= nums[k]:
            l -= 1
        nums[l], nums[k] = nums[k], nums[l]
        # notice len(nums) - 1 is the start index, and k is the end index
        nums[k + 1:] = nums[len(nums) - 1:k:-1]
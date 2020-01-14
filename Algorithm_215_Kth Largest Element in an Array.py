class Solution:
    def findKthLargest(self, nums: List[int], k: int) -> int:
        if len(nums) == 1:
            return nums[0]
        pivot, left, right = nums[0], 0, len(nums) - 1
        while left < right:
            # 必须先减right，后加left，否则会出错。例如考虑[3, 2, 1, 6, 5, 4]
            while nums[right] >= pivot and left < right:
                right -= 1
            while nums[left] <= pivot and left < right:
                left += 1
            nums[left], nums[right] = nums[right], nums[left]
        nums[0], nums[left] = nums[left], nums[0]
        if left == len(nums) - k:
            return nums[left]
        elif left > len(nums) - k: # target lies in the left half
            return self.findKthLargest(nums[:left], k - len(nums) + left)
        else: # target lies in the right half
            return self.findKthLargest(nums[left + 1:], k)
class Solution:
    def sortColors(self, nums: List[int]) -> None:
        zeroIndex, twoIndex, i = 0, len(nums) - 1, 0
        while i <= twoIndex:
            if i > zeroIndex and nums[i] == 0:
                nums[i], nums[zeroIndex] = nums[zeroIndex], nums[i]
                zeroIndex += 1
            elif i < twoIndex and nums[i] == 2:
                nums[i], nums[twoIndex] = nums[twoIndex], nums[i]
                twoIndex -= 1
            else:
                i += 1
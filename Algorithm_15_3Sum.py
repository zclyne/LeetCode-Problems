class Solution:
    def threeSum(self, nums: List[int]) -> List[List[int]]:
        nums.sort()
        result = []
        for i in range(len(nums) - 2):
            # break if nums[i] > 0, which can speeds up the code
            if nums[i] > 0:
                break
            if i == 0 or nums[i] != nums[i - 1]:
                low = i + 1
                high = len(nums) - 1
                while low < high:
                    if nums[i] + nums[low] + nums[high] == 0:
                        result.append([nums[i], nums[low], nums[high]])
                        while low < high and nums[low] == nums[low + 1]:
                            low += 1
                        while low < high and nums[high] == nums[high - 1]:
                            high -= 1
                        low += 1
                        high -= 1
                    elif nums[i] + nums[low] + nums[high] < 0:
                        low += 1
                    else:
                        high -= 1
        return result
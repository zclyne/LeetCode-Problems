# 思路：用标志位repeated记录当前元素是否发生过重复
# 如果nums[i] == nums[i - 1]而repeated == False，表明是第一次重复，可以允许
# 把nums[i]放到用于记录当前长度的变量newLen位置上
# 若repeated == True，则无视该元素，直接进入下一层循环
# 若nums[i] != nums[i - 1]，表明nums[i]是第一次出现，直接放到newLen位置上
# 并把repeated标志置为False，表明是第一次出现

class Solution:
    def removeDuplicates(self, nums: List[int]) -> int:
        if len(nums) <= 2:
            return len(nums)
        newLen = 1
        repeated = False
        for i in range(1, len(nums)):
            if nums[i] == nums[i - 1]:
                if not repeated: # allow repeat for 1 time
                    repeated = True
                    nums[newLen] = nums[i]
                    newLen += 1
            else:
                repeated = False
                nums[newLen] = nums[i]
                newLen += 1
        return newLen
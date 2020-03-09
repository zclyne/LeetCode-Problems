# 注意循环不能写for i in range(maxDistanceCanReach + 1)形式，因为range在循环开始前就确定了循环范围
# 在循环体内改变maxDistance的值不会改变循环次数

class Solution:
    def canJump(self, nums: List[int]) -> bool:
        curPos = maxDistanceCanReach = 0
        while curPos <= maxDistanceCanReach:
            maxDistanceCanReach = max(maxDistanceCanReach, curPos + nums[curPos])
            if maxDistanceCanReach >= len(nums) - 1:
                return True
            curPos += 1
        return False
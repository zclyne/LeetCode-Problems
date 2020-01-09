# 注意循环不能写for i in range(maxDistance + 1)形式，因为range在循环开始前就确定了循环范围
# 在循环体内改变maxDistance的值不会改变循环次数

class Solution:
    def canJump(self, nums: List[int]) -> bool:
        i = maxDistance = 0
        while i <= maxDistance:
            maxDistance = max(maxDistance, i + nums[i])
            if maxDistance >= len(nums) - 1:
                return True
            i += 1
        return False
class Solution:
    def subsets(self, nums: List[int]) -> List[List[int]]:
        self.result = []
        self.backtracking(nums, 0, [])
        return self.result

    def backtracking(self, nums: List[int], pos: int, curSet: List[int]) -> None:
        if pos == len(nums):
            self.result.append(curSet[:])
            return
        # not include nums[pos]
        self.backtracking(nums, pos + 1, curSet)
        # include nums[pos]
        curSet.append(nums[pos])
        self.backtracking(nums, pos + 1, curSet)
        curSet.pop()
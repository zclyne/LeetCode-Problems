class Solution:
    def permute(self, nums: List[int]) -> List[List[int]]:
        self.result = []
        self.visited = [False for _ in range(len(nums))]
        self.backtracking(nums, [])
        return self.result

    def backtracking(self, nums: List[int], arr: List[int]) -> None:
        if len(arr) == len(nums): # successfully found a permutation
            self.result.append(arr[:])
            return
        for i in range(len(nums)):
            if not self.visited[i]:
                self.visited[i] = True
                arr.append(nums[i])
                self.backtracking(nums, arr)
                self.visited[i] = False
                arr.pop()
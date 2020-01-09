class Solution:
    def combinationSum(self, candidates: List[int], target: int) -> List[List[int]]:
        candidates.sort()
        self.result = []
        self.DFS(candidates, len(candidates) - 1, target, [])
        return self.result

    def DFS(self, candidates: List[int], pos: int, target, chosen: List[int]) -> None:
        if target == 0:
            self.result.append(chosen[:])
            return
        if target >= candidates[pos]: # can use candidates[pos]
            chosen.append(candidates[pos])
            self.DFS(candidates, pos, target - candidates[pos], chosen)
            chosen.pop() # remove the appended num
        if pos > 0:
            self.DFS(candidates, pos - 1, target, chosen) # not use candidates[pos]
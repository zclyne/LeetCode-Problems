class Solution:
    def numIslands(self, grid: List[List[str]]) -> int:
        res = 0
        for i in range(len(grid)):
            for j in range(len(grid[0])):
                if grid[i][j] == '1':
                    res += 1
                    self.helper(grid, i, j)
        return res

    def helper(self, grid: List[List[str]], i: int, j: int) -> None:
        if i < 0 or i == len(grid) or j < 0 or j == len(grid[0]) or grid[i][j] != '1':
            return
        grid[i][j] = '-1' # mark as visited
        self.helper(grid, i - 1, j)
        self.helper(grid, i + 1, j)
        self.helper(grid, i, j - 1)
        self.helper(grid, i, j + 1)
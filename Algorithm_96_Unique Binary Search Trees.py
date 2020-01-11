class Solution:
    def numTrees(self, n: int) -> int:
        self.treeNum = [0 for _ in range(n + 1)]
        self.treeNum[0] = self.treeNum[1] = 1
        return self.getTreeNum(n)
    
    def getTreeNum(self, n: int) -> int:
        if self.treeNum[n] != 0: # already calculated numOfTrees for n
            return self.treeNum[n]
        res = 0
        for i in range(1, n + 1):
            res += self.getTreeNum(i - 1) * self.getTreeNum(n - i)
        self.treeNum[n] = res
        return res
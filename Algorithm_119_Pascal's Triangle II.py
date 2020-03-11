class Solution:
    def getRow(self, rowIndex: int) -> List[int]:
        result = [0 for _ in range(rowIndex + 1)]
        result[0] = 1
        for i in range(1, rowIndex + 1):
            for j in range(i, 0, -1):
                result[j] += result[j - 1]
        return result
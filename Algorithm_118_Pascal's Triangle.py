class Solution:
    def generate(self, numRows: int) -> List[List[int]]:
        if numRows == 0:
            return []
        result = [[1]]
        for i in range(1, numRows):
            currentLayer = []
            for j in range(i + 1):
                upperLeft = 0
                if j > 0:
                    upperLeft = result[i - 1][j - 1]
                upperRight = 0
                if j < i:
                    upperRight = result[i - 1][j]
                currentLayer.append(upperLeft + upperRight)
            result.append(currentLayer)
        return result
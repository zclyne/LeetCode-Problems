// 思路：与Algorithm_54采用同样的方法遍历矩阵。变量count记录当前需要放入矩阵的元素，每次循环结束后判断count是否已经超过n^2
// 若满足，说明所有格子中都已有数字，退出while并返回res

class Solution {
    public int[][] generateMatrix(int n) {
        int startRow = 0, endRow = n - 1, startCol = 0, endCol = n - 1, count = 1;
        int[][] res = new int[n][n];
        while (true) {
            // top-left to top-right
            for (int curCol = startCol; curCol <= endCol; curCol++) {
                res[startRow][curCol] = count++;
            }
            startRow++;
            if (count > n * n) break;
            // top-right to bottom-right
            for (int curRow = startRow; curRow <= endRow; curRow++) {
                res[curRow][endCol] = count++;
            }
            endCol--;
            if (count > n * n) break;
            // bottom-right to bottom-left
            for (int curCol = endCol; curCol >= startCol; curCol--) {
                res[endRow][curCol] = count++;
            }
            endRow--;
            if (count > n * n) break;
            // bottom-left to top-left
            for (int curRow = endRow; curRow >= startRow; curRow--) {
                res[curRow][startCol] = count++;
            }
            startCol++;
            if (count > n * n) break;
        }
        return res;
    }
}
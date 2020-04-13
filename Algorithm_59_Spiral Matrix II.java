// 思路：与Algorithm_54采用同样的方法遍历矩阵，变量count记录当前需要放入矩阵的元素

class Solution {
    public int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];
        int topRow = 0, bottomRow = n - topRow - 1, leftCol = topRow, rightCol = n - topRow - 1;
        int count = 1;
        while (topRow <= bottomRow && leftCol <= rightCol) {
            for (int col = leftCol; col <= rightCol; col++) {
                result[topRow][col] = count++;
            }
            for (int row = topRow + 1; row <= bottomRow; row++) {
                result[row][rightCol] = count++;
            }
            if (bottomRow > topRow) {
                for (int col = rightCol - 1; col >= leftCol; col--) {
                    result[bottomRow][col] = count++;
                }
            }
            if (leftCol < rightCol) {
                for (int row = bottomRow - 1; row >= topRow + 1; row--) {
                    result[row][leftCol] = count++;
                }
            }
            topRow++; bottomRow--; leftCol++; rightCol--;
        }
        return result;
    }
}
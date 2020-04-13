// 思路：把以matrix[0][0]为左上角、matrix[i][j]为右下角的矩形中所有元素和存放在sums[i + 1][j + 1]中
// 则计算sumRegion时，只需要把以matrix[row2][col2]为右下角的矩形中所有元素之和
// 减去以matrix[row2][col1 - 1]为右下角的矩形中所有元素之和
// 再减去以matrix[row1 - 1][col2]为右下角的矩形中的所有元素之和
// 再加上以matrix[row1 - 1][col1 - 1]为右下角的矩形中的所有元素之和
// 即为答案

class NumMatrix {
    private int[][] sums;
    public NumMatrix(int[][] matrix) {
        int m = matrix.length, n = matrix.length == 0 ? 0 : matrix[0].length;
        sums = new int[m + 1][n + 1];
        for (int i = 1; i <= matrix.length; i++) {
            for (int j = 1; j <= matrix[0].length; j++) {
                sums[i][j] = matrix[i - 1][j - 1] + sums[i - 1][j] + sums[i][j - 1] - sums[i - 1][j - 1];
            }
        }
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        return sums[row2 + 1][col2 + 1] - sums[row2 + 1][col1] - sums[row1][col2 + 1] + sums[row1][col1];
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */
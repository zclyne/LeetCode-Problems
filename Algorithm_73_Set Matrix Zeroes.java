// 思路：对于matrix[i][j]，若它等于0，则把matrix[i][0]置为0，表示第i行需要被置0，并把matrix[0][j]置0，表示第j列需要被置0
// 注意matrix[0][0]是特殊情况，它同时是第0行和第0列的标志，因此假定matrix[0][0] = 0，表示需要把第0行置0，而第0列是否需要被置零由变量col0表示
// 第一次遍历结束后，对matrix做第二次遍历，此次遍历需要反向，因为标志位在行和列的第一个位置上
// 第二次遍历时，若matrix[i][j]所在的行或列的第一个位置为0，表示matrix[i][j]需要被置为0
// col0的情况要特殊判断

class Solution {
    public void setZeroes(int[][] matrix) {
        int col0 = 1;
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                col0 = 0;
            }
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        for (int i = matrix.length - 1; i >= 0; i--) {
            for (int j = matrix[0].length - 1; j >= 1; j--) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
            if (col0 == 0) {
                matrix[i][0] = 0;
            }
        }
    }
}
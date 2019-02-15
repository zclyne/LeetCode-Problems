// 思路：直接看出交换的顺序
// 注意嵌套循环的循环终止条件
// 由最外圈开始逐渐向内，每层大循环完成一圈的元素交换

class Solution {
    public void rotate(int[][] matrix) {
        for (int row = 0; row < matrix.length / 2; row++) {
            for (int col = row; col < matrix.length - row - 1; col++) {
                int tmp = matrix[row][col];
                matrix[row][col] = matrix[matrix.length - col - 1][row];
                matrix[matrix.length - col - 1][row] = matrix[matrix.length - row - 1][matrix.length - col - 1];
                matrix[matrix.length - row - 1][matrix.length - col - 1] = matrix[col][matrix.length - row - 1];
                matrix[col][matrix.length - row - 1] = tmp;
            }
        }
    }
}